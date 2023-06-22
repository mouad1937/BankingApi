create database bank;

use bank;

create table addresses (
    id int NOT NULL AUTO_INCREMENT,
    street_number varchar(255),
    street_name varchar(255),
    city varchar(255),
    state varchar(255),
    zip varchar(255),
    primary key (id)
);

create table customers (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    address_id int,
    primary key (id),
    foreign key (address_id) references addresses(id)
);

create table accounts (
    id int NOT NULL AUTO_INCREMENT,
    nickname varchar(255),
    rewards int,
    balance decimal,
    type varchar(255),
    customer_id int,
    primary key (id),
    foreign key (customer_id) references customers(id)
);

create table bills (
    id int NOT NULL AUTO_INCREMENT,
    status varchar(255),
    payee varchar(255),
    nickname varchar(255),
    creation_date varchar(255),
    payment_date varchar(255),
    recurring_date int,
    upcoming_payment_date varchar(255),
    payment_amount decimal,
    account_id int,
    primary key (id),
    foreign key (account_id) references accounts(id)
);

create table transactions (
    id int NOT NULL AUTO_INCREMENT,
    type varchar(255),
    transaction_date varchar(255),
    status varchar(255),
    medium varchar(255),
    amount decimal,
    description varchar(255),
    payer_id int,
    payee_id int,
    primary key (id),
    foreign key (payer_id) references accounts(id),
    foreign key (payee_id) references accounts(id)
);