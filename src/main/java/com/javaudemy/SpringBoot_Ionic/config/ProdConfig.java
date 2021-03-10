package com.javaudemy.SpringBoot_Ionic.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.javaudemy.SpringBoot_Ionic.services.EmailService;
import com.javaudemy.SpringBoot_Ionic.services.SmtpEmailService;


@Configuration
@Profile("prod")
public class ProdConfig  {
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	@Bean
	public JavaMailSender getMailHtmlConfigs() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername(System.getenv().get("USERNAME_EMAIL"));
		mailSender.setPassword(System.getenv().get("PASSWORD_EMAIL"));
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		
		return mailSender;
	}
}
