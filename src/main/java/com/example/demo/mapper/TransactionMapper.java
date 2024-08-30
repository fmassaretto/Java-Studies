package com.example.demo.mapper;

import com.example.demo.model.dto.responses.TransactionResponse;
import com.example.demo.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

//    Transaction mapRequestToTransaction(TransactionRequest transactionRequest);

    @Mapping(target="sender", source="senderUser.userUUID")
    @Mapping(target="receiver", source="receiverUser.userUUID")
    TransactionResponse map(Transaction source);

    List<TransactionResponse> mapEntityListToTransactionResponseList(List<Transaction> transaction);
}
