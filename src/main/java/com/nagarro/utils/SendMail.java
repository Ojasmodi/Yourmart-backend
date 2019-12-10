package com.nagarro.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.nagarro.models.Product;

@Component
public class SendMail {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMail(String receiverEmail) throws Exception {
		System.out.println("Sending Email...");

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(receiverEmail);

		msg.setSubject("New comment/suggestion");
		msg.setText("Hi dear \n Admin posted a new comment on your product. \n\n Warm wishes \n YourMart Team");

		javaMailSender.send(msg);

		System.out.println("Done");
	}
	
	public void sendMailToAdmin(List<Product> products,String adminEmail) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(adminEmail);
		
		msg.setSubject("List of products with status NEW/REVIEW since more than 5 days");
		
		msg.setText("Hi dear \n");
		msg.setText("Following are the products \n");
		
		Iterator<Product> it= products.iterator();
		while(it.hasNext()) {
			
			Product p=(Product)it.next();
			System.out.println("Sending Email...");
			msg.setText("Product_Code   Product_Name   Product_Status   Seller_Name \n\n");
			msg.setText(p.getProdCode()+"  "+p.getProdName()+"   "+p.getProdStatus()+"   "+p.getSeller().getOwnerName()+"\n");
			
		}
		
		msg.setText("\n\n Warm wishes \n YourMart Team");
		
		javaMailSender.send(msg);

		System.out.println("Done");

		
	}

}
