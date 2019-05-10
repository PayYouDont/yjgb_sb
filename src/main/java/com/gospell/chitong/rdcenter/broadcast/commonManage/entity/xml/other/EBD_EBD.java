/**   
* @Title: EBD_EBD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:31:14 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBD_EBD 
* @Description: TODO(应急广播)
* @author peiyongdong
* @date 2018年12月13日 下午5:31:14 
*  
*/
@lombok.Data
public class EBD_EBD implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private RelatedEBD RelatedEBD;
		private EBM EBM;
	}
	@lombok.Data
	public static class RelatedEBD {
		private String EBDID;
	}

	@lombok.Data
	public static class EBM {
		private String EBMVersion;
		private String EBMID;
		private MsgBasicInfo MsgBasicInfo;
		private MsgContent MsgContent;
	}

	@lombok.Data
	public static class MsgBasicInfo {
		private String MsgType;
		private String SenderName;
		private String SenderCode;
		private String SendTime;
		private String EventType;
		private String Severity;
		private String StartTime;
		private String EndTime;
	}

	@lombok.Data
	public static class MsgContent {
		private String LanguageCode;
		private String MsgTitle;
		private String MsgDesc;
		private String AreaCode;
		private Auxiliary Auxiliary;
		private String ProgramNum;
	}

	@lombok.Data
	public static class Auxiliary {
		private String AuxiliaryType;
		private String AuxiliaryDesc;
		private String Size;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:34:44
	 */
	@Override
	public EBD_EBD creatResponse() {
		
		return null;
	}
	
	public void setEmergencyinfo(Emergencyinfo emerInfo,ServerProperties properties,String msgType,String ebmId) {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("EBM");
		EBD.RelatedEBD = new RelatedEBD();
		EBD.RelatedEBD.EBDID = EBDcodeUtil.getBaseEBDID();
		EBD.EBM = new EBM();
		EBD.EBM.EBMVersion = "1";
        EBD.EBM.EBMID = ebmId==null?EBDcodeUtil.getEBMID():ebmId;
		EBD.EBM.MsgBasicInfo = new MsgBasicInfo();
		/*
		 * MsgType:
		 * 	1：实际播发,2：取消播发,3：平台演练播发,
		 * 	4：前端演练播发,5：终端演练播发
		 */
		EBD.EBM.MsgBasicInfo.MsgType = msgType;
		EBD.EBM.MsgBasicInfo.SenderName = properties.getBroadcastName();
		EBD.EBM.MsgBasicInfo.SenderCode =properties.getSRC_EBRID();
		EBD.EBM.MsgBasicInfo.SendTime = DateUtils.getDateTime();
		EBD.EBM.MsgBasicInfo.EventType = emerInfo.getAccidentType().getCode();
		EBD.EBM.MsgBasicInfo.Severity = ""+emerInfo.getAccidentLevel().getLevelcode();
		EBD.EBM.MsgBasicInfo.StartTime = DateUtils.formatDateTime(emerInfo.getStartTime());
		EBD.EBM.MsgBasicInfo.EndTime = DateUtils.formatDateTime(emerInfo.getEndTime());
		EBD.EBM.MsgContent = new MsgContent();
		EBD.EBM.MsgContent.LanguageCode = emerInfo.getDisplayLanguage().getShortname();
		EBD.EBM.MsgContent.MsgTitle = emerInfo.getEmergencyname();
		EBD.EBM.MsgContent.AreaCode = emerInfo.getAddresscode ();
		if(emerInfo.getProgramId ()!=null){
		    EBD.EBM.MsgContent.ProgramNum = emerInfo.getProgramId ().toString ();
        }
		if(emerInfo.getMediaId()==null) {
			EBD.EBM.MsgContent.MsgDesc = emerInfo.getContent();
		}else {
			Integer mediaId = emerInfo.getMediaId();
			MediaResouceService mideaService = ApplicationContextRegister.getBean(MediaResouceService.class);
			MediaResouce midea = mideaService.selectById(mediaId);
			EBD.EBM.MsgContent.Auxiliary = new Auxiliary();
			EBD.EBM.MsgContent.Auxiliary.AuxiliaryType = "2";
			EBD.EBM.MsgContent.Auxiliary.AuxiliaryDesc = "EBDR_" + midea.getFileName();
			EBD.EBM.MsgContent.Auxiliary.Size = ""+midea.getFileSize();
		}
	}
}
