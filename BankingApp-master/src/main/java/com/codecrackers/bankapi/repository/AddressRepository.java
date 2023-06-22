package com.codecrackers.bankapi.repository;

import com.codecrackers.bankapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
