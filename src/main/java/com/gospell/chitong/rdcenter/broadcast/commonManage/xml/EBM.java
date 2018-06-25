package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBM extends BaseXML{
	
	private String RelatedEBD_EBDID;
	//EMB;
	private String EBM_EBMVersion;
	private String EBM_EBMID;
	//MsgBasicInfo;
	private String MsgBasicInfo_MsgType;
	private String MsgBasicInfo_SenderName;
	private String MsgBasicInfo_SenderCode;
	private String MsgBasicInfo_SendTime;
	private String MsgBasicInfo_EventType;
	private String MsgBasicInfo_Severity;
	private String MsgBasicInfo_StartTime;
	private String MsgBasicInfo_EndTime;
	// MsgContent;
	private String MsgContent_LanguageCode;
	private String MsgContent_MsgTitle;
	private String MsgContent_MsgDesc;
	private String MsgContent_AreaCode;
	private String Auxiliary_AuxiliaryType;
	private String Auxiliary_AuxiliaryDesc;
	private String Auxiliary_Size;
	@Override
	public Map<String, Object> getMap() {
		//根节点
		Map<String,Object> root = super.getMap();
		Map<String,Object> RelatedEBD = new LinkedHashMap<>();
		RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
		root.put("RelatedEBD",RelatedEBD);
		//根节点下EMB子节点
		Map<String,Object> EMB = new LinkedHashMap<>();
		EMB.put("EBMVersion", getEBM_EBMVersion());
		EMB.put("EBMID", getEBM_EBMID());
		EMB.put("MsgBasicInfo",getMsgBasicInfo());
		EMB.put("MsgContent",getMsgContent());
		root.put("EMB", EMB);
		return root;
	}
	public Map<String,Object> getMsgBasicInfo(){
		Map<String,Object> MsgBasicInfo = new LinkedHashMap<>();
		MsgBasicInfo.put("MsgType", getMsgBasicInfo_MsgType());
		MsgBasicInfo.put("SenderName", getMsgBasicInfo_SenderName());
		MsgBasicInfo.put("SenderCode", getMsgBasicInfo_SenderCode());
		MsgBasicInfo.put("SendTime", getMsgBasicInfo_SendTime());
		MsgBasicInfo.put("EventType", getMsgBasicInfo_EventType());
		MsgBasicInfo.put("Severity", getMsgBasicInfo_Severity());
		MsgBasicInfo.put("StartTime", getMsgBasicInfo_StartTime());
		MsgBasicInfo.put("EndTime", getMsgBasicInfo_EndTime());
		return MsgBasicInfo;
	}
	public Map<String,Object> getMsgContent(){
		Map<String,Object> MsgContent = new LinkedHashMap<>();
		MsgContent.put("LanguageCode",getMsgContent_LanguageCode());
		MsgContent.put("MsgTitle", getMsgContent_MsgTitle());
		MsgContent.put("MsgDesc", getMsgContent_MsgDesc());
		MsgContent.put("AreaCode", getMsgContent_AreaCode());
		Map<String,Object> Auxiliary = new LinkedHashMap<>();
		Auxiliary.put("AuxiliaryType", getAuxiliary_AuxiliaryType());
		Auxiliary.put("AuxiliaryDesc", getAuxiliary_AuxiliaryDesc());
		Auxiliary.put("Size", getAuxiliary_Size());
		MsgContent.put("Auxiliary", Auxiliary);
		return MsgContent;
	}
	/**
	 * exp:BroadcastRequestXML xml = new BroadcastRequestXML();
		xml.setEBDVersion("1");
		xml.setEBDID("10434152300000001030101010000000000000002");
		xml.setEBDType("EBM");
		xml.setSRC_EBRID("43415230000000103010101");
		xml.setSRC_URL("http://127.0.0.1:8080/Emergency/receive/ebd.htm");
		xml.setDEST_EBRID("43415230000000303010201");
		xml.setEBDTime("2018-06-15 21:59:05");
		xml.setRelatedEBD_EBDID("10234000000000001010101010000000000002473");
		xml.setEBMVersion("1");
		xml.setEBMID("43415230000000103010101201802130002");
		xml.setMsgType("3");
		xml.setSenderName("安徽省调度控制平台");
		xml.setSenderCode("23400000000000101010101");
		xml.setSendTime("2018-06-15 21:58:46");
		xml.setEventType("0000");
		xml.setSeverity("1");
		xml.setStartTime("2018-06-15 21:58:46");
		xml.setEndTime("2018-06-15 22:08:46");
		xml.setLanguageCode("zho");
		xml.setMsgTitle("音频演练");
		xml.setMsgDesc("");
		xml.setAreaCode("341523000000");
		xml.setAuxiliaryType("2");
		xml.setAuxiliaryDesc("EBDR_1528773660358_gme.mp3");
		xml.setSize("3569788");
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		XMLUitl.createXML(xml.getMap(), outPath, "播发请求222");
	 */
}
