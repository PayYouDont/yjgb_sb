package com.gospell.chitong.rdcenter.broadcast.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gospell.chitong.rdcenter.broadcast.commonManage.config.SensorTypeAdapter;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

public class JsonUtil {

	public static String toJson(Object object) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(object);
	}

	public static String toJson(Object object, Class<? extends EBD> clazz) {
		Gson gson = new GsonBuilder().registerTypeAdapter(clazz, new SensorTypeAdapter()).create();
		return gson.toJson(object);
	}

	
	public static <T> T toBean(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
 
    }
	public static <T>List<T>  toJsonArray(String jsonStr, Class<T> clazz) {
		 ParameterizedTypeImpl type = new ParameterizedTypeImpl(clazz);
	     Gson gson = new Gson();
	     return gson.fromJson(jsonStr, type);
	}
	 /**
     * 参数类型转换
     */
    private static class ParameterizedTypeImpl implements ParameterizedType {
        private Class<?> clazz;
 
        public ParameterizedTypeImpl(Class<?> clz) {
            clazz = clz;
        }
 
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }
 
        public Type getRawType() {
            return List.class;
        }
 
        public Type getOwnerType() {
            return null;
        }
    }
}
