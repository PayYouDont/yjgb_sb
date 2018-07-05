package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.HashMap;
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
		Map<String,Object> root = new HashMap<>();
		Map<String,Object> RelatedEBD = new LinkedHashMap<>();
		RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
		root.put("RelatedEBD",RelatedEBD);
		//根节点下EMB子节点
		HashMap<String,Object> EMB = new LinkedHashMap<>();
		EMB.put("EBMVersion", getEBM_EBMVersion());
		EMB.put("EBMID", getEBM_EBMID());
		EMB.putAll(getMsgBasicInfo());
		EMB.putAll(getMsgContent());
		root.put("EMB", EMB);
		return EMB;
	}
	public Map<String,Object> getEMBMap(){
		HashMap<String,Object> EMB = new LinkedHashMap<>();
		EMB.put("EBMVersion", getEBM_EBMVersion());
		EMB.put("EBMID", getEBM_EBMID());
		EMB.putAll(getMsgBasicInfo());
		EMB.putAll(getMsgContent());
		//root.put("EMB", EMB);
		return EMB;
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
		MsgContent.putAll(Auxiliary);
		return MsgContent;
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
