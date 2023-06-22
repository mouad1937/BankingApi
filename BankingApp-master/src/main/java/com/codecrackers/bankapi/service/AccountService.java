package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.exception.ResourceNotFoundException;
import com.codecrackers.bankapi.model.Account;
import com.codecrackers.bankapi.model.Customer;
import com.codecrackers.bankapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    @Autowired
    public AccountService(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    public List<Account> getAllAccounts() {
        try {
            return accountRepository.findAll();
        } catch (Exception exception) {
            throw new RuntimeException("Error fetching all accounts");
        }
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", id))
        );
    }

    public Account addAccount(Account accountRequest) {
        Customer customer = accountRequest.customer;
        if(customer.getId() == null) customerService.createCustomer(customer);
        return accountRepository.save(accountRequest);
    }

    public Account updateAccount(Long id, Account accountRequest) {
        Account account = accountRepository.findById(id).orElse(new Account());
        account.nickname = accountRequest.nickname;
        account.balance = accountRequest.balance;
        account.rewards = accountRequest.rewards;
        account.customer = accountRequest.customer;
        try {
            return accountRepository.save(account);
        } catch (Exception exception) {
            throw new RuntimeException("Error updating account");
        }
    }

    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", id))
        );
        try {
            accountRepository.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException("Error deleting account");
        }
    }

}
