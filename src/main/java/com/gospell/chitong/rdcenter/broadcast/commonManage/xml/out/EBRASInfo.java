package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 适配器信息
* @ClassName: EBRASInfo 
* @Description: TODO(  适配器信息   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:31:55 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASInfo extends BaseXML implements ResponseXML{
	/*
	 * EBD/EBRASInfo/Params
	 */
	private String Params_RptStartTime;
	private String Params_RptEndTime;
	private String Params_RptType;
	/*
	 * EBD/EBRASInfo/EBRAS
	 */
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
	
	public Map<String,Object> getParamsMap(){
		Map<String,Object> Params = new LinkedHashMap<>();
		Params.put("RptStartTime", getParams_RptStartTime());
		Params.put("RptEndTime", getParams_RptEndTime());
		Params.put("RptType", getParams_RptType());
		return Params;
	}
	public Map<String,Object> getEBRASMap(){
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
		EBRAS.put("URL", getEBRAS_URL());
		if(getParamsMap()!=null) {
			EBRAS.put("Params", getParamsMap());
		}
		return EBRAS;
	}
	public Map<String,Object> getEBRASInfo(){
		Map<String,Object> EBRASInfo = new LinkedHashMap<>();
		EBRASInfo.put("Params", getParamsMap());
		EBRASInfo.put("EBRAS", getEBRASMap());
		return EBRASInfo;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRASInfo", getEBRASInfo());
		return root;
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:26
	 */
	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:26
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
