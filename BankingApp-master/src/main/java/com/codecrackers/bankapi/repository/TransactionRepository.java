package com.codecrackers.bankapi.repository;

import com.codecrackers.bankapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

     @Query(value = "select * from transactions where type = 'DEPOSIT' and payee_id = ?1", nativeQuery = true)
     List<Transaction> findAllDepositsByAccountId(Long accountId);

     @Query(value = "select * from transactions where type = 'WITHDRAWAL' and payer_id = ?1", nativeQuery = true)
     List<Transaction> findAllWithdrawalsByAccountId(Long accountId);

     @Query(value = "select * from transactions where type = 'P2P' and (payee_id = ?1 or payer_id = ?1)", nativeQuery = true)
     List<Transaction> findAllP2PsByAccountId(Long accountId);

     @Query(value = "select * from transactions where payee_id = ?1 or payer_id = ?1", nativeQuery = true)
     List<Transaction> findAllTransactionsByAccountId(Long accountId);

     @Query(value = "select * from transactions where type = 'DEPOSIT'", nativeQuery = true)
     List<Transaction> findAllDeposits();

     @Query(value = "select * from transactions where type = 'WITHDRAWAL'", nativeQuery = true)
     List<Transaction> findAllWithdrawals();

     @Query(value = "select * from transactions where type = 'P2P'", nativeQuery = true)
     List<Transaction> findAllP2Ps();

}
