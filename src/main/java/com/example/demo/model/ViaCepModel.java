package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepModel(
        @JsonProperty("cep") String zip,
        @JsonProperty("logradouro") String street,
        @JsonProperty("localidade") String city,
        @JsonProperty("uf") String state,
        @JsonProperty("bairro") String neighbor) {
}
