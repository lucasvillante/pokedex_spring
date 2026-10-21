package com.example.pokedex_api.controller;

import com.example.pokedex_api.entities.Pokemon;
import com.example.pokedex_api.entities.RespostaListaPokeApi;
import com.example.pokedex_api.service.PokedexService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {

    private final PokedexService service;

    public PokedexController(PokedexService service) {
        this.service = service;
    }

    @GetMapping("/listar-api")
    public List<RespostaListaPokeApi.ItemPokemon> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/meus-pokemons")
    public List<Pokemon> listarMeusPokemons() {
        return service.listarMeusPokemons();
    }

    @GetMapping("/pesquisar/{nome}")
    public Pokemon pesquisarNaApi(@PathVariable String nome) {
        return service.pesquisarNaApi(nome);
    }

    @PostMapping("/adicionar/{nome}")
    public String adicionarPokemon(@PathVariable String nome) {
        return service.adicionarPokemon(nome);
    }

    @PutMapping("/atualizar/{nome}")
    public String atualizarApelido(@PathVariable String nome, @RequestParam String novoApelido) {
        return service.atualizarApelido(nome, novoApelido);
    }

    @DeleteMapping("/remover/{nome}")
    public String removerPokemon(@PathVariable String nome) {
        return service.removerPokemon(nome);
    }
}