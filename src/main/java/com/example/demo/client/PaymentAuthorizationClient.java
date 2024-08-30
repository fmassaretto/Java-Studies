package com.example.demo.client;

import com.example.demo.client.model.PaymentAuthorization;
import org.springframework.web.service.annotation.GetExchange;

public interface PaymentAuthorizationClient {
    @GetExchange("/api/v2/authorize")
    PaymentAuthorization authorize();
}
