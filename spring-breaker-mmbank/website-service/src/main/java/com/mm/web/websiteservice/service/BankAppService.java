package com.mm.web.websiteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mm.web.websiteservice.controller.BankAppController;
import com.mm.web.websiteservice.dataset.DataSet;
import com.mm.web.websiteservice.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BankAppService {

	@Autowired
	private RestTemplate restTemplate;

	private DataSet dataset;
	
	@HystrixCommand(fallbackMethod = "samplestatement")
	public DataSet getStatement(int offset, int size) {

		int currentSize = size==0?5:size;
		int currentOffset = offset==0?1:offset;
		Link previous = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class).getStatement(currentOffset-currentSize, currentSize)).withRel("previous");
		Link next = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class).getStatement(currentOffset+currentSize, currentSize)).withRel("next");
		DataSet dataSet = restTemplate.getForObject("http://localhost:8763/transactions", DataSet.class);
		System.out.println(dataSet);
		
		List<Transaction> transactionList = dataSet.getTransaction();
		List<Transaction> transactions = new ArrayList<Transaction>();
		for(int value=currentOffset-1; value<currentOffset+currentSize-1; value++) {
			if((transactionList.size() <= value && value > 0) || currentOffset < 1)
				break;
			Transaction transaction = transactionList.get(value);
			transactions.add(transaction);		
		}
		dataSet.setPreviousLink(previous);
		dataSet.setNextLink(next);
		dataSet.setTransaction(transactions);
		System.out.println(dataSet);
		this.dataset=dataSet;
		return dataSet;
	}
	
	public DataSet samplestatement(int offset, int size) {
		return dataset; 
	}

	public void fundTransfer(int senderAccountNumber, int receiverAccountNumber, int amount) {

		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/fundTransfer?sender=" + senderAccountNumber
				+ "&receiver=" + receiverAccountNumber + "&amount=" + amount, null, null);		
	}

	public void withdraw(Transaction transaction) {
		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/withdraw", transaction, null);		
	}

	public void deposit(Transaction transaction) {
		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/deposit", transaction, null);		
	}

	
	
}
