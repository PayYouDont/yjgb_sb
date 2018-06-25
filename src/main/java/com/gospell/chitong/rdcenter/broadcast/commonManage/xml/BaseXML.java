package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

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
}
