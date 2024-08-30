package com.example.demo.service;

import com.example.demo.client.ViaCepClient;
import com.example.demo.client.model.ViaCepModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ViaCepServiceTest {

    @InjectMocks
    private ViaCepService viaCepService;

    @Mock
    private ViaCepClient viaCepClient;

    private ViaCepModel viaCepModel;

    @BeforeEach
    void setUp() {
        viaCepModel = new ViaCepModel("13216-000", "Avenida São João", "Jundiai", "SP", "Vila Joana");
    }

    @Test
    @DisplayName("When Call ViaCepClient Then Should Return ViaCep model")
    void whenCallViaCepClientThenShouldReturnViaCep(){
        when(viaCepClient.getViaCep(anyString())).thenReturn(viaCepModel);

        ViaCepModel result = viaCepService.getCep("13216-000");

        assertEquals(viaCepModel, result);
    }
}