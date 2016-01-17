package com.jasperTest.demo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.util.JSONPObject;

@ControllerAdvice
class Exceptionhander {
	
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
    	
    /*	HttpServletRequest httpServletRequest =((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    				
    	InputStream io = httpServletRequest.getInputStream();
    	
    	String charsetName = httpServletRequest.getCharacterEncoding();
        if (charsetName == null) {
            charsetName = "UTF-8";
        }

        InputStreamReader inputStreamReader = new InputStreamReader(io, charsetName);
        BufferedReader br = new BufferedReader(inputStreamReader);
    	
    	
    	  StringBuilder sb = new StringBuilder();

    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        sb.append(line);
    	    }
    
    	
    	JSONObject jsonObject = new JSONObject(sb.toString());
    	
*/    	
    	e.printStackTrace();
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        
        
        

        // Otherwise setup and send the user to a default error-view.
       
        HashMap<String, String> n = new HashMap<String, String>();
        
        
       return new ResponseEntity<String>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
       
        //return mav;
    }
}

