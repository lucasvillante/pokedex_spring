package com.example.pokedex_api.service;

import com.example.pokedex_api.entities.Pokemon;
import com.example.pokedex_api.entities.RespostaListaPokeApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokedexService {

    private final List<Pokemon> minhaPokedex = new ArrayList<>();
    private final RestTemplate clienteHttp = new RestTemplate();

    public List<RespostaListaPokeApi.ItemPokemon> listarTodos() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=50";
        RespostaListaPokeApi resposta = clienteHttp.getForObject(url, RespostaListaPokeApi.class);
        return resposta.getResultados();
    }

    public List<Pokemon> listarMeusPokemons() {
        return minhaPokedex;
    }

    public Pokemon pesquisarNaApi(String nome) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + nome.toLowerCase();
        return clienteHttp.getForObject(url, Pokemon.class);
    }

    public String adicionarPokemon(String nome) {
        try {
            Pokemon encontrado = pesquisarNaApi(nome);
            minhaPokedex.add(encontrado);
            return "Pokemon adicionado na Pokedex com sucesso!" + encontrado.toString();
        } catch (Exception erro) {
            return "Erro: Pokemon '" + nome + "' não encontrado na PokeAPI.";
        }
    }

    public String atualizarApelido(String nome, String novoApelido) {
        for (Pokemon p : minhaPokedex) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                p.setApelido(novoApelido);
                return "Pokemon atualizado com sucesso!" + p.toString();
            }
        }
        return "Pokemon '" + nome + "' não foi encontrado na sua Pokedex.";
    }

    public String removerPokemon(String nome) {
        for (int i = 0; i < minhaPokedex.size(); i++) {
            if (minhaPokedex.get(i).getNome().equalsIgnoreCase(nome)) {
                Pokemon removido = minhaPokedex.remove(i);
                return "Pokemon '" + removido.getNome() + "' removido da Pokedex!";
            }
        }
        return "Pokemon '" + nome + "' não está na sua Pokedex.";
    }
}