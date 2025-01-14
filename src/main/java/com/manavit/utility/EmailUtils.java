package com.manavit.utility;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
@Component
public class EmailUtils {

	private JavaMailSender javaMailSender;
	public EmailUtils(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	
	
	
	public Boolean sendEmail(String subject,String body,String to,File file) {
	try {
//		creating email object
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();//MimeMessage is an object representing an email.
		MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage, true);// Helps to set email details easily
		messageHelper.setSubject(subject);
		messageHelper.setText(body, true);
		messageHelper.setTo(to);
		messageHelper.addAttachment("plans_info",file );
		javaMailSender.send(mimeMessage);
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return true;
	}
	
}
