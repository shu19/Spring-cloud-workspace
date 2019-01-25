package com.circuitbreaker.service.helloworldservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@GetMapping
	public String hello() {
		return "Hello World";
	}
	
}
