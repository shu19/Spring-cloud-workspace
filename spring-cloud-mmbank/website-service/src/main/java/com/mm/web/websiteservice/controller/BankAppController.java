package com.mm.web.websiteservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mm.web.websiteservice.dataset.DataSet;
import com.mm.web.websiteservice.entity.Transaction;

@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/home")
	public String home() {
		return "index";
	}


	@RequestMapping("/")
	public String depositForm() {
		return "DepositForm";
	}

	
	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/deposit", transaction, null);
		model.addAttribute("message", "Success!");
		return "DepositForm";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "WithdrawForm";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdraw(@ModelAttribute Transaction transaction, Model model) {
		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/withdraw", transaction, null);
		model.addAttribute("message", "Success!");
		return "WithdrawForm";
	}

	@RequestMapping("/fundTransfer")
	public String fundTransfer() {
		return "FundTransferForm";
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber, @RequestParam("amount") int amount,
			Model model) {
		restTemplate.postForEntity("http://TRANSACTION-SERVICE/transactions/fundTransfer?sender=" + senderAccountNumber
				+ "&receiver=" + receiverAccountNumber + "&amount=" + amount, null, null);
		model.addAttribute("message", "Success!");
		return "FundTransferForm";
	}

//	@RequestMapping("/statement")
//	public ModelAndView  statement(Model model) {
//
//		ResponseEntity<DataSet> dataset = restTemplate
//				.getForEntity("http://localhost:9090/transactions?offset=0&size=5", DataSet.class);
//		model.addAttribute("dataset", dataset.getBody());
//		return "Statement";
//	}
//	
	@RequestMapping("/statement")
	public ModelAndView getStatement(@RequestParam("offset") int offset, @RequestParam("size") int size) {
		int currentSize = size==0?5:size;
		int currentOffset = offset==0?1:offset;
		Link previous = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class).getStatement(currentOffset-currentSize, currentSize)).withRel("previous");
		Link next = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BankAppController.class).getStatement(currentOffset+currentSize, currentSize)).withRel("next");
		DataSet dataSet = restTemplate.getForObject("http://ZUUL-SERVICE/transaction/transactions", DataSet.class);
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
		return new ModelAndView("Statement", "dataset", dataSet);
	}

}
