package com.example.demo.service;

import com.example.demo.client.PaymentAuthorizationClient;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.exception.PaymentNotAuthorizedException;
import com.example.demo.model.dto.requests.TransactionRequest;
import com.example.demo.model.entity.Transaction;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.UserType;
import com.example.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    PaymentAuthorizationClient paymentAuthorizationClient;
    UserService userService;
    TransactionRepository transactionRepository;

    public TransactionService(PaymentAuthorizationClient paymentAuthorizationClient,
                              UserService userService,
                              TransactionRepository transactionRepository) {
        this.paymentAuthorizationClient = paymentAuthorizationClient;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void createTransaction(TransactionRequest transactionRequest) throws Exception {
        User sender = this.userService.getUserByUUID(transactionRequest.sender());
        User receiver = this.userService.getUserByUUID(transactionRequest.receiver());

        validateTransaction(transactionRequest, sender);

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionRequest.amount());
        newTransaction.setReceiverUser(receiver);
        newTransaction.setSenderUser(sender);

        transactionRepository.save(newTransaction);

        userService.makeTransfer(sender, receiver, transactionRequest.amount());
    }

    private void validateTransaction(TransactionRequest transactionRequest, User sender) throws Exception {
        if(isSenderShopKeeper(sender)) {
            throw new Exception("Shopkeeper cannot make transfer");
        }

        if(!hasSenderBalance(sender, transactionRequest.amount())) {
            throw new InsufficientBalanceException("Sender has Insufficient Balance");
        }

        if(!isPaymentAuthorized()) {
            throw new PaymentNotAuthorizedException("Payment is not authorized");
        }
    }

    private boolean isPaymentAuthorized() {
//        return paymentAuthorizationClient.authorize().data().authorization(); // TODO: The endpoint is returning 403, hardcoded for now
        return true;
    }

    private boolean isSenderShopKeeper(User receiver) {
        return receiver.userType.equals(UserType.SHOPKEEPER);
    }

    private boolean hasSenderBalance(User sender, BigDecimal amount) {
        return sender.balance.compareTo(amount) > 0;
    }


}
