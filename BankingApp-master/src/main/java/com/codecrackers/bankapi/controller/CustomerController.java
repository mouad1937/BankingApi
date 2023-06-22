package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.model.CustomMessage;
import com.codecrackers.bankapi.service.CustomerService;
import com.codecrackers.bankapi.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "get")
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "post")
    @PostMapping
    public ResponseEntity<CustomMessage> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return  ResponseEntity.status( HttpStatus.CREATED).body(new CustomMessage("200","Customer account updated"));
    }

    @ApiOperation(value = "put")
    @PutMapping("/{id}")
    public ResponseEntity<CustomMessage> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        CustomMessage message=new CustomMessage("200","Customer account updated");
        if (updatedCustomer != null) {
            return new ResponseEntity(updatedCustomer, HttpStatus.OK);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ApiOperation(value = "delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
