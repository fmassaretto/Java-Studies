package com.example.demo.service;

import com.example.demo.clients.ViaCepClient;
import com.example.demo.model.ViaCepModel;
import org.springframework.stereotype.Service;

@Service
public class ViaCepService {

    public ViaCepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    ViaCepClient viaCepClient;

    public ViaCepModel getCep(String cep) {
        return viaCepClient.getViaCep(cep);
    }
}
