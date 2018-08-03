package com.gospell.chitong.rdcenter.broadcast.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;

import java.io.IOException;

public class JsonUtil {
	 private static final ObjectMapper m = new ObjectMapper();
	 public static String toJson(Object object){
	    	String jsonArrayString = null;
	    	try {
	    		jsonArrayString = m.writeValueAsString(object);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	return jsonArrayString;
	    }
	 public static <T> String toJsonArray(Object object) {
		 String jsonStr = JSONArray.fromObject(object).toString();
		 return jsonStr;
	 }
}
