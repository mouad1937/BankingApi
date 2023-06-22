package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.Transaction;
import com.codecrackers.bankapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "get")
    @GetMapping("/accounts/{accountId}/transactions")
    private ResponseEntity<List<Transaction>> getAllTransactionsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllTransactionsByAccountId(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/transactions/{transactionId}")
    private ResponseEntity<Transaction> getTransactionById(@PathVariable Long depositId) {
        return new ResponseEntity<>(transactionService.getTransactionById(depositId), HttpStatus.OK);
    }

}
