package com.gospell.chitong.rdcenter.broadcast.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gospell.chitong.rdcenter.broadcast.commonManage.config.SensorTypeAdapter;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD;

import net.sf.json.JSONArray;

public class JsonUtil {
	 public static <T> String toJsonArray(Object object) {
		 String jsonStr = JSONArray.fromObject(object).toString();
		 return jsonStr;
	 }
	 public static String toJson(Object object) {
		 Gson gson = new GsonBuilder().create();
		 return gson.toJson(object);
	 }
	 public static String toJson(Object object,Class<? extends EBD> clazz) {
		 Gson gson = new GsonBuilder().registerTypeAdapter(clazz,new SensorTypeAdapter()).create();
		 return gson.toJson(object);
	 }
}
