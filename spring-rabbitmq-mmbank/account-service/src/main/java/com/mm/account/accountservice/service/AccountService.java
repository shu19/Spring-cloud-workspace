package com.mm.account.accountservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mm.account.accountservice.entity.Account;

@Service
public interface AccountService {

	List<Account> getAllAccounts();

	Account getAccountById(int accountNumber);

	Double getAccountBalance(int accountNumber);

	void updateBalance(Account account);

	void addNewAccount(Account account);

	void updateAccount(Account account);
}
