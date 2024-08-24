package com.example.demo.controller.integ;

import com.example.demo.controller.ViaCepController;
import com.example.demo.model.ViaCepModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ViaCepControllerIntegTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ViaCepController viaCepController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void whenHitViaCepClientEndpointShouldReturnValidResponse() throws Exception {
        ViaCepModel viaCepModel = new ViaCepModel("13216-000", "Avenida São João", "Jundiai", "Sao PAulo", "Vila Joana");

        given(viaCepController.getCep("13216-000")).willReturn(ResponseEntity.ok(viaCepModel));

        this.mockMvc.perform(get("/v1/cep/13216-000"))
                .andExpect(result -> Objects.requireNonNull(result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("logradouro", is(viaCepModel.street())))
                .andExpect(jsonPath("uf", is(viaCepModel.state())))
                .andExpect(jsonPath("bairro", is(viaCepModel.neighbor())))
                .andExpect(jsonPath("localidade", is(viaCepModel.city())));
    }
}