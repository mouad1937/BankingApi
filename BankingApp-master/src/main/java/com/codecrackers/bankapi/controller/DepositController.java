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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
public class DepositController {

    private final TransactionService transactionService;

    @Autowired
    public DepositController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "post")
    @PostMapping("/accounts/{accountId}/deposits")
    private ResponseEntity<Transaction> createDeposit(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.deposit(accountId, transaction), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get")
    @GetMapping("/accounts/{accountId}/deposits")
    private ResponseEntity<List<Transaction>> getAllDepositsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllDepositsByAccountId(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/deposits/{depositId}")
    private ResponseEntity<Transaction> getDepositById(@PathVariable Long depositId) {
        return new ResponseEntity<>(transactionService.getDepositById(depositId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/deposits")
    private ResponseEntity<List<Transaction>> getAllDeposits() {
        return new ResponseEntity<>(transactionService.getAllDeposits(), HttpStatus.OK);
    }

}
