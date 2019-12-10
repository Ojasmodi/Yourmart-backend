package com.nagarro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nagarro.utils.Notifier;

@SpringBootApplication
public class YourMartApplication {

	public static void main(String[] args) {

		SpringApplication.run(YourMartApplication.class, args);
		Notifier notifier = new Notifier();
		try {
			notifier.setTimerForSendingNotifications();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
