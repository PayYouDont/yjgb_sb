package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 终端信息
* @ClassName: EBRDTInfo 
* @Description: TODO(  终端信息  ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:00:40 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTInfo extends BaseXML implements ResponseXML{
	//EBRDTInfo/EBRDT
	private String EBRDT_RptTime;
	private String EBRDT_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRDT_EBRID;
	private String EBRDT_EBRName;
	private String EBRDT_Longitude;
	private String EBRDT_Latitude;
	
	public Map<String,Object> getEBRDTInfoMap(){
		Map<String,Object> EBRDTInfoMap = new LinkedHashMap<>();
		Map<String,Object> EBRDT = new LinkedHashMap<>();
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
	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	public static String createTar(Deviceinfo deviceInfo) {
		EBRDTInfo info = (EBRDTInfo)BaseXML.createBaseXML(EBRDTInfo.class);
		info.setEBRDT_RptTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		info.setEBRDT_RptType("Sync");
		info.setRelatedEBRPS_EBRID(info.getSRC_EBRID());
		info.setEBRDT_EBRID("");
		return null;
	}
}
