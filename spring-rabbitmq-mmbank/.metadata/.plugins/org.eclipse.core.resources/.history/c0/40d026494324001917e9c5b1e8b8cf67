package com.mm.account.accountservice.resource;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mm.account.accountservice.entity.Account;
import com.mm.account.accountservice.service.AccountService;

@RestController
public class AccountResource {

	@Autowired
	AccountService service;
	
	@PostMapping
	public void addNewAccount(@RequestBody Account account) {
		service.addNewAccount(account);
	}

	@GetMapping
	public List<Account> getAllAccounts() {
		return service.getAllAccounts();
	}

	@PutMapping
	public void updateAccount(@RequestBody Account account) {
		service.updateAccount(account);
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountById(@PathVariable int accountNumber) {
		Account account = service.getAccountById(accountNumber);

		if (account == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@GetMapping("/{accountNumber}/balance")
	public Double getAccountBalance(@PathVariable int accountNumber) {
		return service.getAccountBalance(accountNumber);
	}

	@RabbitListener(queues = "AccountUpdates")
	public void updateBalance(Map<String,Object> map) {
		Account account = service.getAccountById(Integer.parseInt(map.get("accountNumber").toString()));

		int amount =Integer.parseInt( map.get("balance").toString());
		double balance = account.getCurrentBalance() +amount;
		account.setCurrentBalance(balance);
		service.updateBalance(account);
		
	}
	
	@PutMapping("/{accountNumber}")
	public ResponseEntity<Account> updateBalance(@PathVariable int accountNumber,
			@RequestParam("balance") Double amount) {
		Account account = service.getAccountById(accountNumber);

		if (account == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		double balance = account.getCurrentBalance() + amount;
		account.setCurrentBalance(balance);
		service.updateBalance(account);

		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	
}
