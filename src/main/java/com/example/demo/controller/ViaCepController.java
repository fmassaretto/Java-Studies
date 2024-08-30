package com.example.demo.controller;

import com.example.demo.client.model.ViaCepModel;
import com.example.demo.service.ViaCepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cep")
public class ViaCepController {
    ViaCepService cepService;

    public ViaCepController(ViaCepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepModel> getCep(@PathVariable String cep) {
        return ResponseEntity.ok(cepService.getCep(cep));
    }
}
