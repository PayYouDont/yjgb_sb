package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBMStateRequest extends BaseXML{
	private String EBM_EBMID;
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		Map<String,Object> EBMStateRequest = new LinkedHashMap<>();
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMID", getEBM_EBMID());
		EBMStateRequest.put("EBM", EBM);
		root.put("EBMStateRequest", EBMStateRequest);
		return root;
	}
}
