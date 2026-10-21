package com.example.pokedex_api;

import com.example.pokedex_api.controller.PokedexController;
import com.example.pokedex_api.entities.Pokemon;
import com.example.pokedex_api.entities.RespostaListaPokeApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class PokedexApiApplication implements CommandLineRunner {

	private final PokedexController controller;

	public PokedexApiApplication(PokedexController controller) {
		this.controller = controller;
	}

	public static void main(String[] args) {
		SpringApplication.run(PokedexApiApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner leitor = new Scanner(System.in);
		int opcao = -1;

		while (opcao != 0) {
			System.out.println("\nMENU POKEDEX");
			System.out.println("1 - Listar 50 Pokemons da PokeAPI");
			System.out.println("2 - Pesquisar um Pokemon na PokeAPI");
			System.out.println("3 - Adicionar Pokemon na minha Pokedex");
			System.out.println("4 - Ver minha Pokedex");
			System.out.println("5 - Atualizar apelido de um Pokemon da Pokedex");
			System.out.println("6 - Remover Pokemon da minha Pokedex");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = leitor.nextInt();
			leitor.nextLine();

			switch (opcao) {
				case 1:
					List<RespostaListaPokeApi.ItemPokemon> lista50 = controller.listarTodos();
					System.out.println("\n--- 50 Primeiros Pokemons ---");
					for (int i = 0; i < lista50.size(); i++) {
						System.out.println((i + 1) + ". " + lista50.get(i).getNome());
					}
					break;

				case 2:
					System.out.print("Digite o nome do Pokemon para pesquisar: ");
					String nomePesquisa = leitor.nextLine();
					try {
						Pokemon p = controller.pesquisarNaApi(nomePesquisa);
						System.out.println(p);
					} catch (Exception e) {
						System.out.println("Pokemon não encontrado na PokeAPI!");
					}
					break;

				case 3:
					System.out.print("Digite o nome do Pokemon para adicionar na Pokedex: ");
					String nomeAdicionar = leitor.nextLine();
					System.out.println(controller.adicionarPokemon(nomeAdicionar));
					break;

				case 4:
					List<Pokemon> meus = controller.listarMeusPokemons();
					if (meus.isEmpty()) {
						System.out.println("Sua Pokedex ainda está vazia!");
					} else {
						for (Pokemon p : meus) {
							System.out.println(p);
						}
					}
					break;

				case 5:
					System.out.print("Digite o nome do Pokemon que já está na Pokedex: ");
					String nomeAtualizar = leitor.nextLine();
					System.out.print("Digite o novo apelido dele: ");
					String apelido = leitor.nextLine();
					System.out.println(controller.atualizarApelido(nomeAtualizar, apelido));
					break;

				case 6:
					System.out.print("Digite o nome do Pokemon para remover da Pokedex: ");
					String nomeRemover = leitor.nextLine();
					System.out.println(controller.removerPokemon(nomeRemover));
					break;

				case 0:
					System.out.println("Encerrando Pokedex...");
					System.exit(0);
					break;

				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}