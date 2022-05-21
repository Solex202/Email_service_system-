package com.semicolon.africa.Messaging.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.semicolon.africa.Messaging.System")
public class MessagingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingSystemApplication.class, args);
	}

}
