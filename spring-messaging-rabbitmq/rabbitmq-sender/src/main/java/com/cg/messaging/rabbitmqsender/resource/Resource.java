package com.cg.messaging.rabbitmqsender.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.messaging.rabbitmqsender.sender.Sender;

@RestController
public class Resource {

	@Autowired
	private Sender  sender;
	
	@GetMapping
	public String send() {
		sender.send("Shubham ");
		return "sent";
	}
}
