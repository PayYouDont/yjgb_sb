package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBDResponse extends BaseXML{
	private String RelatedEBD_EBDID;
	private String EBDResponse_ResultCode;
	private String EBDResponse_ResultDesc;
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		Map<String,Object> RelatedEBD = new LinkedHashMap<>();
		RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
		root.put("RelatedEBD", RelatedEBD);
		Map<String,Object> EBDResponse = new LinkedHashMap<>();
		EBDResponse.put("ResultCode", getEBDResponse_ResultCode());
		EBDResponse.put("ResultDesc", getEBDResponse_ResultDesc());
		root.put("EBDResponse", EBDResponse);
		return root;
	}
}
