package com.jasperTest.demo.service;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.jasperTest.demo.dao.BaseAbstractDao;

@Component
public  class RequestBodyData {

	
	
	
	
	
	
	public static <E> E getDataFromRequest(String bufferedReader,Class<?> clazz) throws Exception{
		
	    
    	try{    
    		 JSONArray jsonArray  = new JSONArray(bufferedReader.toString());
    	    
    		 JsonNode jsonNode = convertJsonFormat(jsonArray);
    	     ObjectMapper mapper = new ObjectMapper();

    	     return mapper.readValue(new TreeTraversingParser(jsonNode),new TypeReference<List<E>>(){});
    	}
    	catch(JSONException ex){
    		
    		if(ex.getMessage().contains("'[' at character 1"));
    		else
    			throw new Exception(ex.getMessage());
    		
    	}
    	
    	try{
    		JSONObject jsonObject = new JSONObject(bufferedReader.toString());
    		
    		JsonNode jsonNode = convertJsonFormat(jsonObject);
    		ObjectMapper mapper = new ObjectMapper();
    		
    		
    		return (E) mapper.readValue(new TreeTraversingParser(jsonNode),clazz);

    		
    	}
    	catch(JSONException ex)
    	{
    		throw new Exception(ex);
    	}
	}
	
	
	
	

	static JsonNode convertJsonFormat(JSONObject json) {
	    ObjectNode ret = JsonNodeFactory.instance.objectNode();

	    @SuppressWarnings("unchecked")
	    Iterator<String> iterator = json.keys();
	    for (; iterator.hasNext();) {
	        String key = iterator.next();
	        Object value;
	        try {
	            value = json.get(key);
	        } catch (JSONException e) {
	            throw new RuntimeException(e);
	        }
	        if (json.isNull(key))
	            ret.putNull(key);
	        else if (value instanceof String)
	            ret.put(key, (String) value);
	        else if (value instanceof Integer)
	            ret.put(key, (Integer) value);
	        else if (value instanceof Long)
	            ret.put(key, (Long) value);
	        else if (value instanceof Double)
	            ret.put(key, (Double) value);
	        else if (value instanceof Boolean)
	            ret.put(key, (Boolean) value);
	        else if (value instanceof JSONObject)
	            ret.put(key, convertJsonFormat((JSONObject) value));
	        else if (value instanceof JSONArray)
	            ret.put(key, convertJsonFormat((JSONArray) value));
	        else
	            throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
	    }
	    return ret;
	}

	static JsonNode convertJsonFormat(JSONArray json) {
	    ArrayNode ret = JsonNodeFactory.instance.arrayNode();
	    for (int i = 0; i < json.length(); i++) {
	        Object value;
	        try {
	            value = json.get(i);
	        } catch (JSONException e) {
	            throw new RuntimeException(e);
	        }
	        if (json.isNull(i))
	            ret.addNull();
	        else if (value instanceof String)
	            ret.add((String) value);
	        else if (value instanceof Integer)
	            ret.add((Integer) value);
	        else if (value instanceof Long)
	            ret.add((Long) value);
	        else if (value instanceof Double)
	            ret.add((Double) value);
	        else if (value instanceof Boolean)
	            ret.add((Boolean) value);
	        else if (value instanceof JSONObject)
	            ret.add(convertJsonFormat((JSONObject) value));
	        else if (value instanceof JSONArray)
	            ret.add(convertJsonFormat((JSONArray) value));
	        else
	            throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
	    }
	    return ret;
	}

	
	
}
