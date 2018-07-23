
package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRASInfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBRAS 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月19日 上午10:47:40 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASInfoVO extends BaseXML{
	private String EBRAS_RptTime;
	private String EBRAS_RptType;
	//关联平台(可选)
	private String RelatedEBRPS_EBRID;
	//关联站台(可选) 
	private String RelatedEBRST_EBRID;
	private String EBRAS_EBRID;
	private String EBRAS_EBRName;
	private String EBRAS_Longitude;
	private String EBRAS_Latitude;
	private String EBRAS_URL;
	//附加参数(可选)
	private Map<String,Object> EBRAS_Params;
	
	public Map<String,Object> getMap(){
		Map<String,Object> EBRAS = new LinkedHashMap<>();
		EBRAS.put("RptTime", getEBRAS_RptTime());
		EBRAS.put("RptType", getEBRAS_RptType());
		if(getRelatedEBRPS_EBRID()!=null) {
			Map<String,Object> RelatedEBRPS = new LinkedHashMap<>();
			RelatedEBRPS.put("EBRID", getRelatedEBRPS_EBRID());
			EBRAS.put("RelatedEBRPS", RelatedEBRPS);
		}
		if(getRelatedEBRST_EBRID()!=null) {
			Map<String,Object> RelatedEBRST = new LinkedHashMap<>();
			RelatedEBRST.put("EBRID", getRelatedEBRST_EBRID());
			EBRAS.put("RelatedEBRST", RelatedEBRST);
		}
		EBRAS.put("EBRID", getEBRAS_EBRID());
		EBRAS.put("EBRName", getEBRAS_EBRName());
		EBRAS.put("Longitude", getEBRAS_Longitude());
		EBRAS.put("Latitude", getEBRAS_Latitude());
		if(getEBRAS_URL()!=null) {
			EBRAS.put("URL", getEBRAS_URL());
		}
		if(getEBRAS_Params()!=null) {
			EBRAS.put("Params", getEBRAS_Params());
		}
		return EBRAS;
	}
	
	public static List<EBRASInfoVO> getList(List<Deviceinfo> deviceinfos,EBRASInfo EBRASInfo) {
		List<EBRASInfoVO> list = new ArrayList<>();
		for(Deviceinfo info:deviceinfos) {
			EBRASInfoVO ebras = new EBRASInfoVO();
			ebras.setEBRAS_RptTime(DateUtils.getDateTime());
			ebras.setEBRAS_RptType("Sync");
			ebras.setRelatedEBRPS_EBRID(EBRASInfo.getSRC_EBRID());
			ebras.setRelatedEBRST_EBRID(EBRASInfo.getSRC_EBRID());
			//43415231000000314010201
			ebras.setEBRAS_EBRID(EBDcodeUtil.getEBRID(info, "0314010201"));
			ebras.setEBRAS_EBRName(info.getDevaddress()+info.getDevname());
			ebras.setEBRAS_Longitude(info.getLng());
			ebras.setEBRAS_Latitude(info.getLat());
			ebras.setEBRAS_URL("http://192.168.3.234:80");
			list.add(ebras);
		}
		return list;
	}
}
