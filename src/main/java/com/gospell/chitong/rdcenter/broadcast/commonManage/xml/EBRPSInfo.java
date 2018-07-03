package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBRPSInfo extends BaseXML{
	private String EBRPS_RptTime;
	private String EBRPS_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRPS_EBRID;
	private String EBRPS_EBRName;
	private String EBRPS_Address;
	private String EBRPS_Contact;
	private String EBRPS_PhoneNumber;
	private String EBRPS_Longitude;
	private String EBRPS_Latitude;
	private String EBRPS_URL;
	
	public Map<String,Object> getEBRPSInfoMap(){
		Map<String,Object> EBRPSInfoMap = new HashMap<>();
		Map<String,Object> EBRPS = new HashMap<>();
		EBRPS.put("RptTime", getEBRPS_RptTime());
		EBRPS.put("EBRPS_RptType", getEBRPS_RptType());
		Map<String,Object> RelatedEBRPS = new HashMap<>();
		RelatedEBRPS.put("EBRID", getRelatedEBRPS_EBRID());
		EBRPS.put("RelatedEBRPS", RelatedEBRPS);
		EBRPS.put("EBRID", getEBRPS_EBRID());
		EBRPS.put("EBRName", getEBRPS_EBRName());
		EBRPS.put("Address", getEBRPS_Address());
		EBRPS.put("Contact", getEBRPS_Contact());
		EBRPS.put("PhoneNumber", getEBRPS_PhoneNumber());
		EBRPS.put("Longitude", getEBRPS_Longitude());
		EBRPS.put("Latitude", getEBRPS_Latitude());
		EBRPS.put("URL", getEBRPS_URL());
		EBRPSInfoMap.put("EBRPS", EBRPS);
		return EBRPSInfoMap;
	}
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRPSInfo", getEBRPSInfoMap());
		return root;
	}
}
