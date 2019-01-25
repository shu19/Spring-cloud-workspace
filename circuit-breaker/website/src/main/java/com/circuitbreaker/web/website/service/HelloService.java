package com.circuitbreaker.web.website.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	  private final RestTemplate restTemplate;

	  public HelloService (RestTemplate rest) {
	    this.restTemplate = rest;
	  }

	  @HystrixCommand(fallbackMethod = "goodmorning")
	  public String readingList() {
	    URI uri = URI.create("http://localhost:8090");

	    return this.restTemplate.getForObject(uri, String.class);
	  }

	  public String goodmorning() {
	    return "Good Morning!!!";
	  }

}
