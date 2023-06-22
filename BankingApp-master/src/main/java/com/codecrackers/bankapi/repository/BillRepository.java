package com.codecrackers.bankapi.repository;

import com.codecrackers.bankapi.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "select * from bills where account_id = ?1", nativeQuery = true)
    List<Bill> findAllBillsByAccountId(Long id);

}
