package com.nagarro;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class YourMartApplication implements CommandLineRunner {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(YourMartApplication.class, args);
	}

	@Override
	public void run(String... args) throws MessagingException, IOException {

		System.out.println("Sending Email...");

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("ojas.modi@nagarro.com");

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

		System.out.println("Done");

	}



}
