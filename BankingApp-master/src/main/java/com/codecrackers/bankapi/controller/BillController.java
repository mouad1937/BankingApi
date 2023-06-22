package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.Bill;
import com.codecrackers.bankapi.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/accounts/{accountId}")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @ApiOperation(value = "post")
    @PostMapping("/bills")
    public ResponseEntity<Bill> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
        return new ResponseEntity<>(billService.addBill(accountId, bill), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get")
    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills(@PathVariable Long accountId) {
        return new ResponseEntity<>(billService.getAllBillForASpecificAccount(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @PutMapping("/bills/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long accountId, @PathVariable Long billId, @RequestBody Bill bill) {
        return new ResponseEntity<>(billService.updateBill(accountId, billId, bill), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/bills/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        return new ResponseEntity<>(billService.getBillById(billId),HttpStatus.OK);
    }

    @ApiOperation(value = "delete")
    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> removeBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
