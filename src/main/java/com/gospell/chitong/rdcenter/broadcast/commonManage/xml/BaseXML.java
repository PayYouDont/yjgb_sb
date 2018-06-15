package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public abstract class BaseXML {
	protected String EBDVersion;
	protected String EBDID;
	protected String EBDType;
	protected String SRC_EBRID;
	protected String DEST_EBRID;
	protected String EBDTime;
	public Map<String,Object> getMap(){
		Map<String,Object> root = new LinkedHashMap<>();
		root.put("EBDVersion",getEBDVersion());
		root.put("EBDID",getEBDID());
		root.put("EBDType",getEBDType());
		root.put("SRC",getSRC());
		root.put("DEST",getDEST());
		root.put("EBDTime",getEBDTime());
		return root;
	};
	public Map<String,Object> getSRC() {
		Map<String,Object> src = new LinkedHashMap<>();
		src.put("EBRID", getSRC_EBRID());
		return src;
	}
	public Map<String,Object> getDEST() {
		Map<String,Object> dest = new LinkedHashMap<>();
		dest.put("EBRID",getDEST_EBRID());
		return dest;
	}
}
