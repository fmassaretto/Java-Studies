package com.example.demo.controller;

import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.dto.requests.TransactionRequest;
import com.example.demo.model.dto.responses.TransactionResponse;
import com.example.demo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransactionController {
    TransactionService transactionService;
    TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactionResponses = transactionMapper
                .mapEntityListToTransactionResponseList(transactionService.getAllTransactions());
        return ResponseEntity.ok(transactionResponses);
    }

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransactionRequest transactionRequest) throws Exception {
        transactionService.createTransaction(transactionRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction created successfully!");
    }
}
