package com.jasperTest.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jasperTest.demo.dao.AccountStatusDao;
import com.jasperTest.demo.model.AccountStatus;
import com.jasperTest.demo.service.AccountStatusService;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Handles requests for the application home page.
 */

@RequestMapping(value="/home")
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	 AccountStatusService accountStatusService;
	
	@Autowired
		Session session;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value="")
	public String openChatPage(){
		return "index";
	}
	
	
	@RequestMapping(value="/acc",method=RequestMethod.GET)
	public @ResponseBody List<AccountStatus> fetchAll(){
		
		return accountStatusService.findAccountStatusById();
			}
	@RequestMapping(value="/mail")
	public @ResponseBody void snd(){
	
		

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("konguselvampgp@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			 Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
