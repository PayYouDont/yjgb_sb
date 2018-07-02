package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTInfo extends BaseXML{
	//EBRDTInfo/EBRDT
	private String EBRDT_RptTime;
	private String EBRDT_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRDT_EBRID;
	private String EBRDT_EBRName;
	private String EBRDT_Longitude;
	private String EBRDT_Latitude;
	
	public Map<String,Object> getEBRDTInfoMap(){
		Map<String,Object> EBRDTInfoMap = new HashMap<>();
		Map<String,Object> EBRDT = new HashMap<>();
		EBRDT.put("RptTime", getEBRDT_RptTime());
		EBRDT.put("EBRDT_RptType", getEBRDT_RptType());
		EBRDT.put("RelatedEBRPS_EBRID", getRelatedEBRPS_EBRID());
		EBRDT.put("EBRDT_EBRID", getEBRDT_EBRID());
		EBRDT.put("EBRDT_EBRName", getEBRDT_EBRName());
		EBRDT.put("EBRDT_Longitude", getEBRDT_Longitude());
		EBRDT.put("EBRDT_Latitude", getEBRDT_Latitude());
		EBRDTInfoMap.put("EBRDT", EBRDT);
		return EBRDTInfoMap;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRDTInfo", getEBRDTInfoMap());
		return root;
	}
}
