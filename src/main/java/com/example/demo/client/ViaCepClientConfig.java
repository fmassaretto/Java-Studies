package com.example.demo.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ViaCepClientConfig {
    String baseUrl = "https://viacep.com.br/";

    @Bean
    ViaCepClient viaCepClient(WebClient.Builder builder) {
        var wca = WebClientAdapter.create(builder.baseUrl(baseUrl).build());
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(wca)
                .build()
                .createClient(ViaCepClient.class);
    }
}
