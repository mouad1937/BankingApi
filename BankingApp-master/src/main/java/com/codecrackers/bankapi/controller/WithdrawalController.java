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
public class WithdrawalController {

    private final TransactionService transactionService;

    @Autowired
    public WithdrawalController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "post")
    @PostMapping("/accounts/{accountId}/withdrawals")
    private ResponseEntity<Transaction> withdraw(@PathVariable Long accountId, @RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.withdraw(accountId, transaction), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get")
    @GetMapping("/accounts/{accountId}/withdrawals")
    private ResponseEntity<List<Transaction>> getAllWithdrawalsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllWithdrawalsByAccountId(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/withdrawals/{withdrawalId}")
    private ResponseEntity<Transaction> getWithdrawalById(@PathVariable Long withdrawalId) {
        return new ResponseEntity<>(transactionService.getWithdrawalById(withdrawalId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/withdrawals")
    private ResponseEntity<List<Transaction>> getAllWithdrawals() {
        return new ResponseEntity<>(transactionService.getAllWithdrawals(), HttpStatus.OK);
    }

}
