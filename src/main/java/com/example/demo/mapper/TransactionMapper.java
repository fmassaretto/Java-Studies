package com.example.demo.mapper;

import com.example.demo.model.dto.requests.TransactionRequest;
import com.example.demo.model.dto.responses.TransactionResponse;
import com.example.demo.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TransactionMapper {

    Transaction mapRequestToTransaction(TransactionRequest transactionRequest);

    // TODO: sender and receiver in target class are null
    @Mapping(target = "sender", source = "transaction.senderUser.userUUID")
    @Mapping(target = "receiver", source = "transaction.receiverUser.userUUID")
    List<TransactionResponse> mapEntityListToTransactionResponseList(List<Transaction> transaction);
}
