package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 播发记录XML
* @ClassName: BroadcastRecordXML 
* @Description: TODO(用于生成播发记录xml) 
* @author peiyongdong
* @date 2018年6月15日 下午4:27:19 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBMBrdLog extends BaseXML implements ResponseXML{
	//EBMBrdLog/Params
	private String Params_RptStartTime;
	private String Params_RptEndTime;
	//EBMBrdLog/EBMBrdItem/EBM
	private String EBM_EBMID;
	//EBMBrdLog/EBMBrdItem/EBM/MsgBasicInfo
	private String MsgBasicInfo_MsgType;
	private String MsgBasicInfo_SenderName;
	private String MsgBasicInfo_SenderCode;
	private String MsgBasicInfo_SendTime;
	private String MsgBasicInfo_EventType;
	private String MsgBasicInfo_Severity;
	private String MsgBasicInfo_StartTime;
	private String MsgBasicInfo_EndTime;
	//EBMBrdLog/EBMBrdItem/EBM/MsgContent
	private String MsgContent_LanguageCode;
	private String MsgContent_MsgTitle;
	private String MsgContent_MsgDesc;
	private String MsgContent_AreaCode;
	private String MsgContent_ProgramNum;
	//EBMBrdLog/EBMBrdItem/UnitInfo/Unit
	private String EBRPS_EBRID;
	//EBMBrdLog/EBMBrdItem/UnitInfo/Unit
	private String Unit_UnitID;
	private String Unit_UnitName;
	private String Unit_PersonID;
	private String Unit_PersonName;
	//EBMBrdLog/EBMBrdItem
	private String EBMBrdItem_BrdStateCode;
	private String EBMBrdItem_BrdStateDesc;
	
	
	//EBMBrdLog/EBMBrdItem/Coverage
	private String Coverage_CoverageRate;
	private String Coverage_AreaCode;
	private String Coverage_ResBrdStat;
	//EBMBrdLog/EBMBrdItem/ResBrdInfo
	private String ResBrdInfo_ResBrdItem;
	
	public Map<String, Object> getParamsMap() {
		Map<String,Object> ParamsMap = new LinkedHashMap<>();
		ParamsMap.put("RptStartTime", getParams_RptStartTime());
		ParamsMap.put("RptEndTime", getParams_RptEndTime());
		return ParamsMap;
	}
	public Map<String, Object> getMsgBasicInfoMap() {
		Map<String,Object> MsgBasicInfoMap = new LinkedHashMap<>();
		MsgBasicInfoMap.put("MsgType", getMsgBasicInfo_MsgType());
		MsgBasicInfoMap.put("SenderName", getMsgBasicInfo_SenderName());
		MsgBasicInfoMap.put("SenderCode", getMsgBasicInfo_SenderCode());
		MsgBasicInfoMap.put("SendTime", getMsgBasicInfo_SendTime());
		MsgBasicInfoMap.put("EventType", getMsgBasicInfo_EventType());
		MsgBasicInfoMap.put("Severity", getMsgBasicInfo_Severity());
		MsgBasicInfoMap.put("StartTime", getMsgBasicInfo_StartTime());
		MsgBasicInfoMap.put("EndTime", getMsgBasicInfo_EndTime());
		return MsgBasicInfoMap;
	}
	public Map<String, Object> getMsgContentMap() {
		Map<String,Object> MsgContentMap = new LinkedHashMap<>();
		MsgContentMap.put("LanguageCode", getMsgContent_LanguageCode());
		MsgContentMap.put("MsgTitle", getMsgContent_MsgTitle());
		MsgContentMap.put("MsgDesc", getMsgContent_MsgDesc());
		MsgContentMap.put("AreaCode", getMsgContent_AreaCode());
		MsgContentMap.put("ProgramNum", getMsgContent_ProgramNum());
		return MsgContentMap;
		
	}
	public Map<String, Object> getUnitMap() {
		Map<String,Object> UnitMap = new LinkedHashMap<>();
		Map<String,Object> EBRPS = new LinkedHashMap<>();
		EBRPS.put("EBRID", EBRPS_EBRID);
		UnitMap.put("EBRPS", EBRPS);
		UnitMap.put("UnitID", getUnit_UnitID());
		UnitMap.put("UnitName", getUnit_UnitName());
		UnitMap.put("PersonID", getUnit_PersonID());
		UnitMap.put("PersonName", getUnit_PersonName());
		return UnitMap;
	}
	public Map<String, Object> getCoverageMap() {
		Map<String,Object> Coverage = new LinkedHashMap<>();
		Coverage.put("CoverageRate", getCoverage_CoverageRate());
		Coverage.put("AreaCode", getCoverage_AreaCode());
		Coverage.put("ResBrdStat", getCoverage_ResBrdStat());
		return Coverage;
	}
	public Map<String, Object> getEBMBrdItemMap() {
		Map<String,Object> EBMBrdItem = new LinkedHashMap<>();
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMID", getEBM_EBMID());
		EBM.put("MsgBasicInfo", getMsgBasicInfoMap());
		EBM.put("MsgContent", getMsgContentMap());
		EBMBrdItem.put("EBM", EBM);
		Map<String,Object> UnitInfo = new LinkedHashMap<>();
		UnitInfo.put("Unit", getUnitMap());
		EBMBrdItem.put("BrdStateCode", getEBMBrdItem_BrdStateCode());
		EBMBrdItem.put("BrdStateDesc", getEBMBrdItem_BrdStateDesc());
		EBMBrdItem.put("Coverage", getCoverageMap());
		return EBMBrdItem;
	}
	@Override
	public Map<String, Object> getMap() {
		//根节点
		Map<String,Object> root = super.getMap();
		//根节点下EBMBrdLog子节点
		Map<String,Object> EBMBrdLog = new LinkedHashMap<>();
		EBMBrdLog.put("Params", getParamsMap());
		EBMBrdLog.put("EBMBrdItem", getEBMBrdItemMap());
		root.put("EBMBrdLog", EBMBrdLog);
		return root;
	}
	/** 
	 * <p>Title: getResultCode</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultCode() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:52:45
	 */
	@Override
	public String getResultCode() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: getResultDesc</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultDesc() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:52:45
	 */
	@Override
	public String getResultDesc() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:53:21
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
	 * @date 2018年7月17日 上午11:53:21
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}
