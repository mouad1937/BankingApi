package com.codecrackers.bankapi.controller;

import com.codecrackers.bankapi.service.AddressService;
import com.codecrackers.bankapi.model.Address;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.Optional;

@Api
@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation(value = "post")
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        logger.info("Creating new address: {}", address);
        return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get")
    @GetMapping
    public ResponseEntity<Iterable<Address>> getAllAddresses() {
        logger.debug("Retrieving all addresses");
        Iterable<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @ApiOperation(value = "get")
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        logger.debug("Retrieving address with ID: {}", id);
        Address address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @ApiOperation(value = "put")
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        logger.debug("Updating address with ID: {}, New address: {}", id, address);
        return new ResponseEntity<>(addressService.updateAddress(id, address), HttpStatus.OK);
    }

    @ApiOperation(value = "delete")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        logger.warn("Deleting address with ID: {}", id);
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
