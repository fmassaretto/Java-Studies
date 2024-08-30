package com.example.demo.exception;

public class PaymentNotAuthorizedException extends RuntimeException {
    public PaymentNotAuthorizedException(String message) {
        super(message);
    }
}
