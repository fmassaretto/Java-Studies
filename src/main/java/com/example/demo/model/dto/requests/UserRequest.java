package com.example.demo.model.dto.requests;

import com.example.demo.model.enums.UserType;

import java.math.BigDecimal;

public record UserRequest(String username,
                          String email,
                          String cpf,
                          String password,
                          BigDecimal balance,
                          UserType userType) {
}
