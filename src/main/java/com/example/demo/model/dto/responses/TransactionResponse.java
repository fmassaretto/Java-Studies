package com.example.demo.model.dto.responses;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionResponse(BigDecimal amount, UUID sender, UUID receiver) {
}
