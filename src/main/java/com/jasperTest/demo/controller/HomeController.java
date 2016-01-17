package com.jasperTest.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.h2.tools.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.jasperTest.demo.dao.AccountStatusDao;
import com.jasperTest.demo.model.AccountStatus;
import com.jasperTest.demo.service.AccountStatusService;
import com.jasperTest.demo.service.RequestBodyData;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 * @param <E>
 */

@RequestMapping(value="/home")
@Controller
public class HomeController<E> {
	
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
	
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public @ResponseBody Object getObj(){
		
		
		return accountStatusService.findAllStatus();
	}
	
	@RequestMapping(value="/postToDB",method=RequestMethod.POST)
	public @ResponseBody Object saveIt(@RequestBody AccountStatus accountStatusMaster) throws IOException, JSONException{
		
		HttpServletRequest httpServletRequest =((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();	
	 BufferedReader br = httpServletRequest.getReader();
	    	
	    	
   	  StringBuilder sb = new StringBuilder();

   	    String line;
   	    while ((line = br.readLine()) != null) {
   	        sb.append(line);
   	    }
   
   	
   	JSONObject jsonObject = new JSONObject(sb.toString());


   	//JsonNode jsonNode = convertJsonFormat(jsonObject);
   	//ObjectMapper mapper = new ObjectMapper();
   	//AccountStatus myPojo = mapper.readValue(new TreeTraversingParser(jsonNode), AccountStatus.class);
   	
		
		return accountStatusService.create(accountStatusMaster);
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public @ResponseBody String pos(@RequestBody Object obj, HttpServletRequest httpServletRequest) throws Exception {
	
		System.out.println(obj.toString());
		
/*		InputStream io = httpServletRequest.getInputStream();
    	
    	String charsetName = httpServletRequest.getCharacterEncoding();
        if (charsetName == null) {
            charsetName = "UTF-8";
        }

        InputStreamReader inputStreamReader = new InputStreamReader(io, charsetName);*/
        
        BufferedReader br = httpServletRequest.getReader();
    	
    	
    	  StringBuilder sb = new StringBuilder();

    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        sb.append(line);
    	    }
    	    
    	AccountStatus status =   RequestBodyData.getDataFromRequest(sb.toString(),AccountStatus.class);
    	    
    	List<AccountStatus> accountStatus =  RequestBodyData.getDataFromRequest(sb.toString(),List.class); 
    	
    	    	
    	
    	
    	
  //  	System.out.println(jsonObject.toString());
		
		return null;
		//	throw new Exception();
	}
	
	
	public static <E> E comp(E nn){
		
		return nn;
		
	}
	
	@RequestMapping(value="")
	public String openChatPage(){
				
		
		
		
		return "index";
	}
	
	
	@RequestMapping(value="/acc",method=RequestMethod.GET)
	public @ResponseBody List<AccountStatus> fetchAll(){
		
		return accountStatusService.findAccountStatusById();
			}
	
	
	@RequestMapping(value="/startH2")
	public void startH2() throws SQLException{
		
		Server server = Server.createWebServer("-webAllowOthers","-webPort","8081").start(); 
		server.start();
		
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
	/*
	 try{
	    	Resource resource = new ClassPathResource("/logback.xml");
	    
	    
	    	String uri= resource.getURI().toString();
	    	
	    	uri = uri.substring(0, uri.lastIndexOf("/"));
	    	uri = uri.replace("file:/", "");
	    	
	    	uri +="/H2_File/";
	    	
	       
	        dataSource.setDriverClassName("org.h2.jdbcx.JdbcDataSource");
	        dataSource.setUrl("jdbc:h2:"+uri+"cache_db;MODE=MYSQL;");
	        dataSource.setUsername("");
	        dataSource.setPassword("");
	 }*/
	
}
