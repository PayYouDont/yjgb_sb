package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	 private static ObjectMapper m = new ObjectMapper();
	 public static String toJson(Object object){
	    	String jsonArrayString = null;
	    	try {
	    		jsonArrayString = m.writeValueAsString(object);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	return jsonArrayString;
	    }
}
