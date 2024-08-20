package com.example.demo.clients;

import com.example.demo.model.ViaCepModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ViaCepClient {
    @GetExchange("/ws/{cep}/json/")
    ViaCepModel getViaCep(@PathVariable String cep);
}
