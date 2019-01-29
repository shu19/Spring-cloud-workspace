package com.mm.transaction.transactionservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mm.transaction.transactionservice.entity.Transaction;

@Service
public interface TransactionService {


	Double deposit(Integer accountNumber, String transactionDetails, Double currentBalance, Double amount);

	Double withdraw(Integer accountNumber, String transactionDetails, Double currentBalance, Double amount);

	void fundTransfer(int senderTransaction, Double senderBalance, int receiverTransaction, Double amount, Double amount2);

	List<Transaction> getStatement();
 
}