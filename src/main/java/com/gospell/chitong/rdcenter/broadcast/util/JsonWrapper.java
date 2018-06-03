package com.gospell.chitong.rdcenter.broadcast.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonWrapper {

	public static HashMap<String, Object> successWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		return wrapper;
	}
	
	public static HashMap<String, Object> successWrapper(Object pojo,String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("data", pojo);
		wrapper.put("msg", msg);
		return wrapper;
	}
	
	public static HashMap<String, Object> successWrapperMsg(String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("msg", msg);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapper(Object pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("data", pojo);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapperMsg(String msg) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(false));
		wrapper.put("msg", msg);
		return wrapper;
	}

	public static HashMap<String, Object> failureWrapper(Object... pojo) {
		return wrapper(false, pojo);
	}

	public static HashMap<String, Object> successWrapper(Object... pojo) {
		return wrapper(true, pojo);
	}

	public static HashMap<String, Object> wrapper(boolean success,
			Object... pojo) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(success));
		int length = pojo.length;
		if (pojo.length % 2 == 1) {
			length--;
		}
		length /= 2;
		for (int i = 0; i < length; i++) {
			wrapper.put(String.valueOf(pojo[(i * 2)]), pojo[(i * 2 + 1)]);
		}
		return wrapper;
	}

	public static HashMap<String, Object> wrapperMap(boolean success,
			Map<String, Object> map) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(success));
		wrapper.put("data", map);
		return wrapper;
	}

	public static Map<String, Object> wrapperDataRows(List<Object> dataRows) {
		HashMap<String, Object> wrapper = new HashMap<String, Object>();
		wrapper.put("success", Boolean.valueOf(true));
		wrapper.put("Rows", dataRows);
		return wrapper;
	}
	  //转换为Jquery easyUI适配的Json数组
    public static <T> String wrapperPage(List <T> list,int total){
    	ObjectMapper m = new ObjectMapper();
    	String jsonArrayString = null;
    	Object [] rows = list.toArray();
    	PageJson pageJson = new PageJson(total, rows);
    	try {
    		jsonArrayString = m.writeValueAsString(pageJson);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return jsonArrayString;
    }
}
