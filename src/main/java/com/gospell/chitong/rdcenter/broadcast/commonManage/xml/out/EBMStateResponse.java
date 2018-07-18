package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 播发状态回执
* @ClassName: EBMStateResponse 
* @Description: TODO(  播发状态回执   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:20:31 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBMStateResponse extends BaseXML implements ResponseXML{
	//EBM/EBMStateResponse
	private String EBMStateResponse_RptTime;
	//EBMStateResponse/EBM
	private String EBM_EBMID;
	//EBM/EBMStateResponse
	private String EBMStateResponse_BrdStateCode;
	private String EBMStateResponse_BrdStateDesc;
	//EBM/EBMStateResponse/ResBrdInfo/ResBrdItem/EBRBS
	private String EBRBS_RptTime;
	private String EBRBS_BrdSysInfo;
	private String EBRBS_StartTime;
	private String EBRBS_EndTime;
	private String EBRBS_FileURL;
	private String EBRBS_BrdStateCode;
	private String EBRBS_BrdStateDesc;
	
	public Map<String,Object> getEBRBSMap(){
		Map<String,Object> EBRBS = new LinkedHashMap<>();
		EBRBS.put("RptTime", getEBRBS_RptTime());
		EBRBS.put("BrdSysInfo", getEBRBS_BrdSysInfo());
		EBRBS.put("StartTime", getEBRBS_StartTime());
		EBRBS.put("EndTime", getEBRBS_EndTime());
		EBRBS.put("FileURL", getEBRBS_FileURL());
		EBRBS.put("BrdStateCode", getEBRBS_BrdStateCode());
		EBRBS.put("BrdStateDesc", getEBRBS_BrdStateDesc());
		return EBRBS;
	}
	
	public Map<String,Object> getResBrdItemMap(){
		Map<String,Object> ResBrdItem = new LinkedHashMap<>();
		ResBrdItem.put("EBRBS", getEBRBSMap());
		return ResBrdItem;
	}
	public Map<String,Object> getResBrdInfoMap(){
		Map<String,Object> ResBrdInfo = new LinkedHashMap<>();
		ResBrdInfo.put("ResBrdItem", getResBrdItemMap());
		return ResBrdInfo;
	}
	public Map<String,Object> getEBMStateResponseMap(){
		Map<String,Object> EBMStateResponse = new LinkedHashMap<>();
		EBMStateResponse.put("RptTime", getEBMStateResponse_RptTime());
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMID", getEBM_EBMID());
		EBMStateResponse.put("EBM", EBM);
		EBMStateResponse.put("BrdStateCode", getEBMStateResponse_BrdStateCode());
		EBMStateResponse.put("BrdStateDesc", getEBMStateResponse_BrdStateDesc());
		EBMStateResponse.put("ResBrdInfo", getResBrdInfoMap());
		return EBMStateResponse;
	}
	@Override
	public Map<String,Object> getMap() {
		Map<String,Object> root = super.getMap();
		root.put("EBMStateResponse", getEBMStateResponseMap());
		return root;
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:32:29
	 */
	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return ResponseXML.super.createFullEntity();
	}
	/** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:32:33
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return ResponseXML.super.createIncrementalEntity();
	}
}
