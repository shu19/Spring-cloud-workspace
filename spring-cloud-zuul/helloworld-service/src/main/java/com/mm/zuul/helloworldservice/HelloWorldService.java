package com.mm.zuul.helloworldservice;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldService {
	
	@GetMapping
	public String hello() {
		return "<h1>Hello World!!!</h1>";
	}
	
	@GetMapping ("hello")
	public String helloWorld() {
		return  "hello  <strong style=\"color: red;\">" + "World" + " </strong> Responsed on : " + new Date();	
	}
}
