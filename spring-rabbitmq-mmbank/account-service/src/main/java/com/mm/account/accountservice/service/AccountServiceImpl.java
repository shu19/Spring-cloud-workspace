package com.mm.account.accountservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.account.accountservice.entity.Account;
import com.mm.account.accountservice.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repository;
	


	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}
	
	@Override
	public Account getAccountById(int accountNumber) {
		return repository.findById(accountNumber).get();		
	}

	@Override
	public Double getAccountBalance(int accountNumber) {
		return repository.findById(accountNumber).get().getCurrentBalance();
	}


	@Override
	public void updateBalance(Account account) {
		repository.save(account);
	}

	@Override
	public void addNewAccount(Account account) {
		repository.save(account);
	}

	@Override
	public void updateAccount(Account account) {
		repository.save(account);
	}
	
}
