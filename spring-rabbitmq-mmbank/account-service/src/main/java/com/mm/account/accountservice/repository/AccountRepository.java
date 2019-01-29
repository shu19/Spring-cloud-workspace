package com.mm.account.accountservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mm.account.accountservice.entity.Account;

public interface AccountRepository extends MongoRepository<Account, Integer>{ 

}
