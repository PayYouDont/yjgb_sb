package com.gospell.chitong.rdcenter.broadcast.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.GsonIgnore;
import com.gospell.chitong.rdcenter.broadcast.commonManage.config.SensorTypeAdapter;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdTypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.EsayuiData;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.EsayuiData.Rows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static Map<String, Object> toCMDChar(String cmd) {
		EsayuiData data = toBean(cmd, EsayuiData.class);
		ArrayList<Rows> rows = data.getRows();
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> typeMap = new HashMap<>();
		typeMap.put("nameEn","array");
		List<CmdType> list  = ApplicationContextRegister.getBean(CmdTypeMapper.class).list(typeMap);
		CmdType cmdType = null;
		if (list!=null&&list.size()>0){
			cmdType = list.get(0);
		}
		for (Rows row : rows) {
			String key = row.getAttrName();
			Object value = row.getAttrValue();
			Integer attrType = Integer.valueOf(row.getAttrType());
			if(cmdType!=null&&attrType==cmdType.getId()){
				value = value.toString().split(",");
			}
			map.put(key, value);
		}
		//String CMDChar = toJson(map);
		return map;
	}
	
	public static String toJson(Object object) {
		GsonBuilder builder = new GsonBuilder();
		builder.setExclusionStrategies(new GsonIgnoreExclusionStrategy());
		Gson gson = builder.create();
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
	public static int i = 0;
	private static class GsonIgnoreExclusionStrategy implements ExclusionStrategy {

		@Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getAnnotation(GsonIgnore.class)!=null;
		}

		@Override
		public boolean shouldSkipClass(Class<?> clazz) {
			return clazz.getAnnotation(GsonIgnore.class)!=null;
		}
		
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
