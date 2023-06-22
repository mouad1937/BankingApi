package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.errorHandler.AddressNotFoundException;
import com.codecrackers.bankapi.exception.ResourceNotFoundException;
import com.codecrackers.bankapi.model.Address;
import com.codecrackers.bankapi.repository.AddressRepository;
import com.codecrackers.bankapi.repository.CustomerRepository;
import com.codecrackers.bankapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    private void verifyCustomer(Long customerId) throws AddressNotFoundException {
        if (!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(
                    " code : 404 , " + " Error updating customer with id :" + customerId ));
        }
    }
    public List<Customer> getAllCustomers() {

       try {
           return customerRepository.findAll();
       }catch (Exception e){
           throw new RuntimeException("code : 404 " + ", message : error fetching customers");
       }
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        for(Address address : customer.getAddresses()) {
            if (address.getId() == null) addressRepository.save(address);
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        verifyCustomer(id);
        Customer existingCustomer = customerRepository.findById(id).orElse(new Customer());
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddresses(updatedCustomer.getAddresses());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("code : 404 , message: This id does not exist in customers")));
        try {
            customerRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Fail deleting this customer");
        }
    }

}
