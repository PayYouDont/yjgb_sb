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
	private String RptStartTime;
	private String RptEndTime;
	private EBM EBM = new EBM();
	@Data
	public class EBM{
		public String EBMID;
		public String MsgType;
		public String SenderName;
		public String SenderCode;
		public String SendTime;
		public String EventType;
		public String Severity;
		public String StartTime;
		public String EndTime;
		public String LanguageCode;
		public String MsgTitle;
		public String MsgDesc;
		public String AreaCode;
		public String ProgramNum;
		public Map<String,Object> getMsgBasicInfo(){
			Map<String,Object> MsgBasicInfo = new LinkedHashMap<>();
			MsgBasicInfo.put("MsgType",getMsgType());
			MsgBasicInfo.put("SenderName",getSenderName());
			MsgBasicInfo.put("SenderCode",getSenderCode());
			MsgBasicInfo.put("SendTime",getSendTime());
			MsgBasicInfo.put("EventType",getEventType());
			MsgBasicInfo.put("Severity",getSeverity());
			MsgBasicInfo.put("StartTime",getStartTime());
			MsgBasicInfo.put("EndTime",getEndTime());
			return MsgBasicInfo;
		}
		public Map<String,Object> getMsgContent(){
			Map<String,Object> MsgContent = new LinkedHashMap<>();
			MsgContent.put("LanguageCode",getLanguageCode());
			MsgContent.put("MsgTitle",getMsgTitle());
			MsgContent.put("MsgDesc",getMsgDesc());
			MsgContent.put("AreaCode",getAreaCode());
			MsgContent.put("ProgramNum",getProgramNum());
			return MsgContent;
		}
		public Map<String,Object> getMap(){
			Map<String,Object> EBM = new LinkedHashMap<>();
			EBM.put("EBMID",getEBDID());
			EBM.put("MsgBasicInfo",getMsgBasicInfo());
			EBM.put("MsgContent",getMsgContent());
			return EBM;
		}
	}
	private UnitInfo UnitInfo = new UnitInfo();
	@Data
	public class UnitInfo{
		public String EBRID;
		public String UnitID;
		public String UnitName;
		public String PersonID;
		public String PersonName;
		public Map<String,Object> getUnit(){
			Map<String,Object> Unit = new LinkedHashMap<>();
			Map<String,Object> EBRPS = new LinkedHashMap<>();
			EBRPS.put("EBRID", getEBRID());
			Unit.put("EBRID", EBRPS);
			Unit.put("UnitID", getUnitID());
			Unit.put("UnitName", getUnitName());
			Unit.put("PersonID", getPersonID());
			Unit.put("PersonName", getPersonName());
			return Unit;
		}
		public Map<String,Object> getMap(){
			Map<String,Object> UnitInfo = new LinkedHashMap<>();
			UnitInfo.put("Unit", getUnit());
			return UnitInfo;
		}
	}
	private String CoverageRate;
	private String AreaCode;
	private String ResBrdStat;
	private String BrdStateCode;
	private String BrdStateDesc;
	private ResBrdInfo ResBrdInfo = new ResBrdInfo();
	@Data
	public class ResBrdInfo{
		public Map<String, Object> getMap() {
			Map<String,Object> ResBrdItem = new LinkedHashMap<>();
			//ResBrdItem.put("", "");
			return ResBrdItem;
		}
	}
	@Override
	public Map<String, Object> getMap() {
		//根节点
		Map<String,Object> root = super.getMap();
		//根节点下EBMBrdLog子节点
		Map<String,Object> EBMBrdLog = new LinkedHashMap<>();
		//EBMBrdLog节点下Params子节点
		Map<String,Object> Params = new LinkedHashMap<>();
		//EBMBrdLog节点下EBMBrdItem子节点
		Map<String,Object> EBMBrdItem = new LinkedHashMap<>();
		Params.put("RptStartTime", getRptStartTime());
		Params.put("RptEndTime", getRptEndTime());
		EBMBrdItem.put("EBM", getEBM().getMap());
		EBMBrdItem.put("UnitInfo", getUnitInfo().getMap());
		EBMBrdItem.put("BrdStateCode",getBrdStateCode());
		EBMBrdItem.put("BrdStateDesc",getBrdStateDesc());
		//EBMBrdItem节点下Coverage子节点
		Map<String,Object> Coverage = new LinkedHashMap<>();
		Coverage.put("CoverageRate", getCoverageRate());
		Coverage.put("AreaCode", getAreaCode());
		Coverage.put("ResBrdStat", getResBrdStat());
		EBMBrdItem.put("Coverage", Coverage);
		EBMBrdItem.put("ResBrdInfo", getResBrdInfo().getMap());
		EBMBrdLog.put("Params", Params);
		EBMBrdLog.put("EBMBrdItem", EBMBrdItem);
		root.put("EBMBrdLog", EBMBrdLog);
		return root;
	}
	/**
	 * exp:
	 * BroadcastRecordXML xml = new BroadcastRecordXML();
		xml.setEBDVersion("1");
		xml.setEBDID("10234000000000001010101010000000000000001");
		xml.setEBDType("EBMBrdLog");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		xml.setEBDTime("2017-06-07 13:40:36");
		xml.setRptStartTime("YYYY-MM-DD HH:MI:SS");
		xml.setRptEndTime("YYYY-MM-DD HH:MI:SS");
		EBM ebm = xml.getEBM();
		ebm.setEBMID("23400000000000101010101");
		ebm.setMsgType("1");
		ebm.setSenderName("某应急办");
		ebm.setSenderCode("1234");
		ebm.setSendTime("YYYY-MM-DD HH:MI:SS");
		ebm.setEventType("11A01");
		ebm.setSeverity("2");
		ebm.setStartTime("YYYY-MM-DD HH:MI:SS");
		ebm.setEndTime("YYYY-MM-DD HH:MI:SS");
		ebm.setLanguageCode("zho");
		ebm.setMsgTitle("暴雨黄色预警");
		ebm.setMsgDesc("下雨啦收衣服啦");
		ebm.setAreaCode("341523000000,341523000001");
		ebm.setProgramNum("12");
		UnitInfo unitInfo = xml.getUnitInfo();
		unitInfo.setEBRID("33415000000000101010101");
		unitInfo.setUnitID("007");
		unitInfo.setUnitName("应急办");
		unitInfo.setPersonID("008");
		unitInfo.setPersonName("刘德华");
		xml.setBrdStateCode("3");
		xml.setBrdStateDesc("播发成功");
		xml.setCoverageRate("0.95");
		xml.setAreaCode("341523000000,341523000001");
		xml.setResBrdStat("1,4,4,118");
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		createXML(xml.getMap(),outPath,"播发记录2");
	 */
}
