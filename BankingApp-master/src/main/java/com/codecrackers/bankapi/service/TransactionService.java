package com.codecrackers.bankapi.service;

import com.codecrackers.bankapi.exception.ResourceNotFoundException;
import com.codecrackers.bankapi.model.Account;
import com.codecrackers.bankapi.model.Transaction;
import com.codecrackers.bankapi.repository.AccountRepository;
import com.codecrackers.bankapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codecrackers.bankapi.enumeration.Type.DEPOSIT;
import static com.codecrackers.bankapi.enumeration.Type.P2P;
import static com.codecrackers.bankapi.enumeration.Type.WITHDRAWAL;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction deposit(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        account.balance += transaction.getAmount();
        accountRepository.save(account);
        transaction.setPayeeAccount(account);
        transaction.setType(DEPOSIT);
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        account.balance -= transaction.getAmount();
        accountRepository.save(account);
        transaction.setPayerAccount(account);
        transaction.setType(WITHDRAWAL);
        return transactionRepository.save(transaction);
    }

    public Transaction p2p(Transaction transaction) {
        Long payerId = transaction.getPayerAccount().id;
        Account payerAccount = accountRepository.findById(payerId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", payerId))
        );
        Long payeeId = transaction.getPayeeAccount().id;
        Account payeeAccount = accountRepository.findById(payeeId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", payeeId))
        );
        double amount = transaction.getAmount();
        payerAccount.balance -= amount;
        payeeAccount.balance += amount;
        accountRepository.saveAll(List.of(payerAccount, payeeAccount));
        transaction.setType(P2P);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllDepositsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllDepositsByAccountId(accountId);
    }

    public List<Transaction> getAllWithdrawalsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllWithdrawalsByAccountId(accountId);
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllTransactionsByAccountId(accountId);
    }

    public List<Transaction> getAllP2PsByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Account with ID %s not found", accountId))
        );
        return transactionRepository.findAllP2PsByAccountId(accountId);
    }

    public List<Transaction> getAllDeposits() {
        return transactionRepository.findAllDeposits();
    }

    public List<Transaction> getAllWithdrawals() {
        return transactionRepository.findAllWithdrawals();
    }

    public List<Transaction> getAllP2Ps() {
        return transactionRepository.findAllP2Ps();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getDepositById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Deposit with ID %s not found", id))
        );
        if(!transaction.getType().equals(DEPOSIT)) {
            throw new RuntimeException(String.format("Transaction with ID %s is not a Deposit", id));
        }
        return transaction;
    }

    public Transaction getWithdrawalById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Withdrawal with ID %s not found", id))
        );
        if(!transaction.getType().equals(WITHDRAWAL)) {
            throw new RuntimeException(String.format("Transaction with ID %s is not a Withdrawal", id));
        }
        return transaction;
    }

    public Transaction getP2PById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Withdrawal with ID %s not found", id))
        );
        if(!transaction.getType().equals(P2P)) {
            throw new RuntimeException(String.format("Transaction with ID %s is not a P2P", id));
        }
        return transaction;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Transaction with ID %s not found", id))
        );
    }

}
