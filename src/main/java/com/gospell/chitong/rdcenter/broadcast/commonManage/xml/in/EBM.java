package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 信息播发
* @ClassName: EBM 
* @Description: TODO(  信息播发   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:28:45 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBM extends BaseXML{
	
	private String RelatedEBD_EBDID;
	//EBM;
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
	
	public void setEBM(Emergencyinfo emerInfo,ServerProperties properties) {
		RelatedEBD_EBDID = EBDcodeUtil.getBaseEBDID();
		EBM_EBMVersion = "1";
		EBM_EBMID = EBDcodeUtil.getEBMID();
		/*
		 * 1：实际播发,2：取消播发,3：平台演练播发,
		 * 4：前端演练播发,5：终端演练播发
		 */
		MsgBasicInfo_MsgType = "3";
		MsgBasicInfo_SenderName = properties.getBroadcastName();
		MsgBasicInfo_SenderCode =properties.getSRC_EBRID();
		MsgBasicInfo_SendTime = DateUtils.getDateTime();
		MsgBasicInfo_EventType = emerInfo.getAccidentType().getCode();
		MsgBasicInfo_Severity = ""+emerInfo.getAccidentLevel().getLevelcode();
		MsgBasicInfo_StartTime = DateUtils.formatDateTime(emerInfo.getStartTime());
		MsgBasicInfo_EndTime = DateUtils.formatDateTime(emerInfo.getEndTime());
		MsgContent_LanguageCode = emerInfo.getDisplayLanguage().getShortname();
		MsgContent_MsgTitle = emerInfo.getEmergencyname();
		MsgContent_AreaCode = emerInfo.getAreacode();
		if(emerInfo.getMediaId()==null) {
			MsgContent_MsgDesc = emerInfo.getContent();
		}else {
			Integer mediaId = emerInfo.getMediaId();
			MediaResouceService mideaService = ApplicationContextRegister.getBean(MediaResouceService.class);
			MediaResouce midea = mideaService.selectById(mediaId);
			Auxiliary_AuxiliaryType = "2";
			Auxiliary_AuxiliaryDesc = midea.getFileName();
			Auxiliary_Size = ""+midea.getFileSize();
		}
	}
	@Override
	public Map<String, Object> getMap() {
		//根节点
		Map<String,Object> root = super.getMap();
		Map<String,Object> RelatedEBD = new LinkedHashMap<>();
		RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
		root.put("RelatedEBD",RelatedEBD);
		//根节点下EBM子节点
		root.put("EBM", getEBMMap());
		return root;
	}
	public Map<String,Object> getEBMMap(){
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMVersion", getEBM_EBMVersion());
		EBM.put("EBMID", getEBM_EBMID());
		EBM.put("MsgBasicInfo",getMsgBasicInfo());
		EBM.put("MsgContent",getMsgContent());
		return EBM;
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
		if(Auxiliary_AuxiliaryType!=null) {
			Auxiliary.put("AuxiliaryType", getAuxiliary_AuxiliaryType());
		}
		if(Auxiliary_AuxiliaryDesc!=null) {
			Auxiliary.put("AuxiliaryDesc", getAuxiliary_AuxiliaryDesc());
		}
		if(Auxiliary_Size!=null) {
			Auxiliary.put("Size", getAuxiliary_Size());
		}
		if(Auxiliary.size()>0) {
			MsgContent.put("Auxiliary",Auxiliary);
		}
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
