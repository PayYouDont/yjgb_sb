package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import lombok.Data;

@Data
public class BaseXML {
	//命名规则为"上级节点名_下级节点名"格式，如下：
	protected String EBD_EBDVersion;
	protected String EBD_EBDID;
	protected String EBD_EBDType;
	protected String SRC_EBRID;
	protected String SRC_URL;
	protected String DEST_EBRID;
	protected String EBD_EBDTime;
	public Map<String,Object> getMap(){
		Map<String,Object> root = new LinkedHashMap<>();
		root.put("EBDVersion",getEBD_EBDVersion());
		root.put("EBDID",getEBD_EBDID());
		root.put("EBDType",getEBD_EBDType());
		root.put("SRC",getSRC());
		root.put("DEST",getDEST());
		root.put("EBDTime",getEBD_EBDTime());
		return root;
	};
	public Map<String,Object> getSRC() {
		Map<String,Object> src = new LinkedHashMap<>();
		src.put("EBRID", getSRC_EBRID());
		if(getSRC_URL()!=null) {
			src.put("URL", getSRC_URL());
		}
		return src;
	}
	public Map<String,Object> getDEST() {
		Map<String,Object> dest = new LinkedHashMap<>();
		dest.put("EBRID",getDEST_EBRID());
		return dest;
	}
	public static Class<? extends BaseXML> getClassByEBDType(String EBDType) {
		switch (EBDType) {
		case "OMDRequest":
			return OMDRequest.class;
		case "EBM":
			return EBM.class;
		case "EBMStateRequest":
			return EBMStateRequest.class;
		case "ConnectionCheck":
			return HeartXML.class;
		case "EBDResponse":
			return EBDResponse.class;
		default:
			return BaseXML.class;
		}
	}
	public BaseXML getResponseByClass(BaseXML entity) {
		EBDResponse response = new EBDResponse();
		response.setEBD_EBDVersion(entity.getEBD_EBDVersion());
		response.setEBD_EBDID(entity.getEBD_EBDID());
		response.setEBD_EBDType("EBDResponse");
		response.setSRC_EBRID(entity.getDEST_EBRID());
		response.setDEST_EBRID(entity.getSRC_EBRID());
		response.setEBD_EBDTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getSubMapByKey(String key){
		Object subMap = getMap().get(key);
		if(subMap instanceof Map) {
			return (Map<String, Object>) subMap;
		}else {
			return null;
		}
	}
	/**
	 * 添加重复子集到map
	 * @Title: addSubMap 
	 * @Description: TODO(解决某些xml有些重复的标签，但是值不同问题) 
	 * @param @param key
	 * @param @param map
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月2日 下午5:06:58
	 */
	public Map<String,Object> addSubMap(String key,Map<String,Object> map){
		Map<String,Object> submap = getSubMapByKey(key);
		Map<String,Object> root = getMap();
		if(submap!=null) {
	 		Map<String,Object> IdentityMap = new IdentityHashMap<>();
	 		Set<Entry<String, Object>> set = submap.entrySet();
	 		String IdentityKey = "";
	 		for (Entry<String, Object> entry : set) {
				String ekey = entry.getKey();
				Object evalue = entry.getValue();
				IdentityMap.put(ekey, evalue);
				IdentityKey = ekey;
			}
	 		IdentityMap.put(new String(IdentityKey),map);
	 		root.put(key, IdentityMap);
	 	}
		return root;
	}
}
