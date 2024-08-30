package com.example.demo.model.entity;

import com.example.demo.model.enums.UserType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_uuid")
    public UUID userUUID;
    public String username;
    @Column(unique = true)
    public String email;
    @Column(unique = true)
    public String cpf;
    public String password;
    public BigDecimal balance;
    public UserType userType;
}