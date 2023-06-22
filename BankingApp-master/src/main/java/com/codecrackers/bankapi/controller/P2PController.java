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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("/transactions/p2p")
public class P2PController {

    private final TransactionService transactionService;

    @Autowired
    public P2PController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "post")
    @PostMapping
    private ResponseEntity<Transaction> createP2PTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.p2p(transaction), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/{accountId}")
    private ResponseEntity<List<Transaction>> getP2PsByAccountId(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAllP2PsByAccountId(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping
    private ResponseEntity<List<Transaction>> getAllP2Ps() {
        return new ResponseEntity<>(transactionService.getAllP2Ps(), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/{transactionId}")
    private ResponseEntity<Transaction> getP2PById(@PathVariable Long transactionId) {
        return new ResponseEntity<>(transactionService.getP2PById(transactionId), HttpStatus.OK);
    }

}
