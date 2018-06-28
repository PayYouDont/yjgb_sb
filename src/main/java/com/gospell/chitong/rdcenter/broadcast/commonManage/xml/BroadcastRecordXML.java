package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class BroadcastRecordXML extends BaseXML{
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
	 * 根据实体类生成回执实体
	 * @Title: getResponseByClass 
	 * @Description: TODO(具体回执暂未处理，目前只写了统一回复方式) 
	 * @param @param entity
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月28日 上午8:45:18
	 */
	@Override
	public BaseXML getResponseByClass(BaseXML entity) {
		EBDResponse response = (EBDResponse) super.getResponseByClass(entity);
		response.setEBDResponse_ResultCode("1");
		response.setEBDResponse_ResultDesc("已完成接收");
		return response;
	}
}
