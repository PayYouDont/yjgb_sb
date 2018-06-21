package com.gospell.chitong.rdcenter.broadcast.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflecUtil {
	
	public static List<Field> getFields(Class<?> clazz) {
		//属性集合
		List<Field> fieldList = new ArrayList<>();
		//获取实体类属性
		Field[] fields = clazz.getDeclaredFields();
		fieldList = addFildToList(fieldList, fields);
		//获取父类属性
		if(clazz.getSuperclass() instanceof Class) {
			Field[] supfields = clazz.getSuperclass().getDeclaredFields();
			fieldList = addFildToList(fieldList, supfields);
		}
		return fieldList;
	}
	public static List<Field> addFildToList(List<Field> fieldList,Field[] fields){
		for(int i=0;i<fields.length;i++) {
			Field field = fields[i];
			field.setAccessible(true); 
			fieldList.add(field);
		}
		return fieldList;
	}
}
