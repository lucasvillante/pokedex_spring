package com.example.pokedex_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaListaPokeApi {

    @JsonProperty("results")
    private List<ItemPokemon> resultados;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ItemPokemon {

        @JsonProperty("name")
        private String nome;
        private String url;

        @Override
        public String toString() {
            return "\n------------------------------" +
                    "\nNome: " + nome +
                    "\nURL: " + url;
        }
    }
}