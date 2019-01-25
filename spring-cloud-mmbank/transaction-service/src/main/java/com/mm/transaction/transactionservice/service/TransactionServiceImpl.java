package com.mm.transaction.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.transaction.transactionservice.entity.Transaction;
import com.mm.transaction.transactionservice.entity.TransactionType;
import com.mm.transaction.transactionservice.repository.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Double deposit(Integer accountNumber, String transactionDetails, Double currentBalance, Double amount) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDetails(transactionDetails);
		transaction.setAmount(amount);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		currentBalance += amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		System.out.println("deposit : "+transaction);
		transactionRepository.save(transaction);
		return currentBalance;
	}

	@Override
	public Double withdraw(Integer accountNumber, String transactionDetails, Double currentBalance, Double amount) {
		Transaction transaction = new Transaction();

		transaction.setAccountNumber(accountNumber);
		transaction.setTransactionDetails(transactionDetails);
		transaction.setAmount(amount);
		transaction.setTransactionType(TransactionType.WITHDRAW);

		
		
		if (currentBalance > amount) {
			currentBalance -= amount;
		}

		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		System.out.println("withdraw : "+transaction);
	
		transactionRepository.save(transaction);
		return currentBalance;
	}


	@Override
	public List<Transaction> getStatement() {
		return transactionRepository.findAll();
	}

	@Override
	public void fundTransfer(int senderTransaction, Double senderBalance, int receiverTransaction, Double amount,
			Double amount2) {
		// TODO Auto-generated method stub
		
	}

}
