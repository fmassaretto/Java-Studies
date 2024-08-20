package com.example.demo.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ViaCepClientConfig {
    String baseUrl = "https://viacep.com.br/";

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(){
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
    }

    @Bean
    public ViaCepClient httpExchangeAdapter(HttpServiceProxyFactory factory){
        return factory.createClient(ViaCepClient.class);
    }
}
