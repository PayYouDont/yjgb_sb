package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRDTInfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBRID 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月19日 下午4:51:14 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTInfoVO extends BaseXML{
	private String EBRDT_RptTime;
	private String EBRDT_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRDT_EBRID;
	private String EBRDT_EBRName;
	private String EBRDT_Longitude;
	private String EBRDT_Latitude;
	
	public Map<String,Object> getMap(){
		Map<String,Object> EBRDT = new LinkedHashMap<>();
		EBRDT.put("RptTime", getEBRDT_RptTime());
		EBRDT.put("EBRDT_RptType", getEBRDT_RptType());
		Map<String,Object> RelatedEBRPS = new HashMap<>();
		RelatedEBRPS.put("EBRID", getRelatedEBRPS_EBRID());
		EBRDT.put("RelatedEBRPS", RelatedEBRPS);
		EBRDT.put("EBRID", getEBRDT_EBRID());
		EBRDT.put("EBRName", getEBRDT_EBRName());
		EBRDT.put("Longitude", getEBRDT_Longitude());
		EBRDT.put("Latitude", getEBRDT_Latitude());
		return EBRDT;
	}
	public static List<EBRDTInfoVO> getList(List<Deviceinfo> deviceinfos,EBRDTInfo EBRDTInfo) {
		List<EBRDTInfoVO> list = new ArrayList<>();
		for(Deviceinfo info:deviceinfos) {
			EBRDTInfoVO ebrdt = new EBRDTInfoVO();
			ebrdt.setEBRDT_RptTime(DateUtils.getDateTime());
			ebrdt.setEBRDT_RptType("Sync");
			ebrdt.setRelatedEBRPS_EBRID(EBRDTInfo.getSRC_EBRID());
			//43415231000000314010201
			ebrdt.setEBRDT_EBRID(EBDcodeUtil.getEBRID(info, "0314010201"));
			ebrdt.setEBRDT_EBRName(info.getDevaddress()+info.getDevname());
			ebrdt.setEBRDT_Longitude(info.getLng());
			ebrdt.setEBRDT_Latitude(info.getLat());
			list.add(ebrdt);
		}
		return list;
	}
}
