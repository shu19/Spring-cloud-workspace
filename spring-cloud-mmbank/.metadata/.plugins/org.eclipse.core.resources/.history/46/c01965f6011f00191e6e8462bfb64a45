package com.moneymoney.app.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.app.transactionservice.entity.Transaction;
import com.moneymoney.app.transactionservice.entity.TransactionType;
import com.moneymoney.app.transactionservice.repository.TransactionRepository;

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
	public void fundTransfer(int senderAccountNumber,Double senderBalance, int receiveAccountNumber,Double receiverBalance,Double amount) {
		
		Transaction senderTransaction=new Transaction();
		senderTransaction.setAccountNumber(senderAccountNumber);
		senderTransaction.setAmount(amount);
		senderTransaction.setCurrentBalance(senderBalance-amount);
//		senderTransaction.set
		
		if(senderTransaction.getAmount()<=senderBalance) {
			senderTransaction.setCurrentBalance(senderBalance-senderTransaction.getAmount());
//			receiverTransaction.setCurrentBalance(currentBalance);
//			transactionRepository.save(senderTransaction);
//			transactionRepository.save(receiverTransaction);
		}
	}

	@Override
	public List<Transaction> getStatement() {
		return transactionRepository.findAll();
	}

}
