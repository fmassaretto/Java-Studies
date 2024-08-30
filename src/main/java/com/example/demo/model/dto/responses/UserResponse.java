package com.example.demo.model.dto.responses;

import com.example.demo.model.enums.UserType;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponse(UUID uuid,
                           String username,
                           String email,
                           BigDecimal balance,
                           UserType userType) {
}
