package com.example.demo.client;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class PaymentAuthorizationClientConfig {
    String baseUrl = "https://util.devi.tools/";

    @Bean
    PaymentAuthorizationClient paymentAuthorizationClient(WebClient.Builder builder) {
        var wca = WebClientAdapter.create(builder.baseUrl(baseUrl)
                .defaultHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0")
                .build());
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(wca)
                .build()
                .createClient(PaymentAuthorizationClient.class);
    }
}
