package com.example.pokedex_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private int id;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("height")
    private int altura;

    @JsonProperty("weight")
    private int peso;

    private String apelido;

    @Override
    public String toString() {
        return "\n==============================" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nApelido: " + (apelido != null ? apelido : "Sem apelido") +
                "\nAltura: " + altura +
                "\nPeso: " + peso +
                "\n==============================";
    }
}