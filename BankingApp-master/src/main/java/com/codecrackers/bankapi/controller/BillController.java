package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.Bill;
import com.codecrackers.bankapi.model.CustomMessage;
import com.codecrackers.bankapi.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

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
    public ResponseEntity<CustomMessage> createBill(@PathVariable Long accountId, @RequestBody Bill bill) {
        billService.addBill(accountId, bill);
        CustomMessage message=new CustomMessage(" 201","Created bill and added it to the account" );
        return  ResponseEntity.status( HttpStatus.CREATED).body(message);
    }

    @ApiOperation(value = "get")
    @GetMapping("/bills")
    public ResponseEntity<List<Bill>> getAllBills(@PathVariable Long accountId) {
        return new ResponseEntity<>(billService.getAllBillForASpecificAccount(accountId), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @PutMapping("/bills/{billId}")
    public ResponseEntity<CustomMessage> updateBill(@PathVariable Long accountId, @PathVariable Long billId, @RequestBody Bill bill) {
        billService.updateBill(accountId, billId, bill);
       CustomMessage message= new CustomMessage("202","Accepted bill modification");
        return  ResponseEntity.status( HttpStatus.OK).body(message);
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
