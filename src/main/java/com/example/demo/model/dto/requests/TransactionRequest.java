package com.example.demo.model.dto.requests;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(BigDecimal amount, UUID sender, UUID receiver) {
}
