package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_uuid")
    public UUID transactionUUID;

    public BigDecimal amount;

    @ManyToOne
    public User senderUser;

    @ManyToOne
    public User receiverUser;
}
