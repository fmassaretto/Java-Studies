package com.example.demo.controller;

import com.example.demo.model.ViaCepModel;
import com.example.demo.service.ViaCepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ViaCepControllerTest {

    public static final String CEP = "13216-070";
    public static final String STREET = "Rua 123";
    public static final String CITY = "Jundiai";
    public static final String STATE = "SP";
    public static final String NEIGHBOR = "Vila Joana";
    @InjectMocks
    private ViaCepController controller;

    @Mock
    private ViaCepService viaCepService;

    private ViaCepModel viaCepModel;


    @BeforeEach
    void setUp() {
        viaCepModel = new ViaCepModel(CEP, STREET, CITY, STATE, NEIGHBOR);
    }

    @Test
    @DisplayName("When Calls GetCep Then Should Return ViaCep Model")
    void whenCallsGetCepThenShouldReturnViaCepModel() {
        when(viaCepService.getCep(anyString())).thenReturn(viaCepModel);

        ResponseEntity<ViaCepModel> result = controller.getCep(CEP);

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(ResponseEntity.class, result.getClass());

        assertEquals(CITY, result.getBody().city());
        assertEquals(STATE, result.getBody().state());
        assertEquals(NEIGHBOR, result.getBody().neighbor());
    }
}