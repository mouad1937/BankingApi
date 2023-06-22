package com.codecrackers.bankapi.model;

import com.codecrackers.bankapi.enumeration.AccountType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String nickname;

    @Column
    public Integer rewards;

    @Column
    public Double balance;

    @Column
    public AccountType type;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer customer;

}
