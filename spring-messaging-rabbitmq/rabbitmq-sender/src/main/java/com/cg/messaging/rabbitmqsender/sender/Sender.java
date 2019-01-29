package com.cg.messaging.rabbitmqsender.sender;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Sender {

	@Autowired
	RabbitMessagingTemplate template;
	
	public void send(String message) {
		template.convertAndSend("CustomerQ",message);
	}
	
}
