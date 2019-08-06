/**   
* @Title: NodeNew.java 
* @Package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月17日 上午10:20:08 
*/
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import lombok.Data;

import java.io.Serializable;

/** 
* @ClassName: NodeNew 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月17日 上午10:20:08 
*  
*/
@Data
public class NodeNews implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String EBMVersion;
	private String EBMID;
	private String MsgType;
	private String SenderName;
	private String SenderCode;
	private String SendTime;
	private String EventType;
	private String Severity;
	private String StartTime;
	private String EndTime;
	private String LanguageCode;
	private String MsgTitle;
	private String MsgDesc;
	private String AreaCode;
	private String AuxiliaryType;
	private String AuxiliaryDesc;
	private String Size;
	public static NodeNews parseEBD(EBD ebd){
		if(!(ebd instanceof EBD_EBM)) {
			return null;
		}
		EBD_EBM EBD = (EBD_EBM)ebd;
		NodeNews nodeNews = new NodeNews();
		nodeNews.setEBMVersion(EBD.getEBD().getEBDVersion());
		EBD_EBM.EBM EBM = EBD.getEBD().getEBM();
		nodeNews.setEBMID(EBM.getEBMID());
		nodeNews.setMsgType(EBM.getMsgBasicInfo().getMsgType());
		nodeNews.setSenderName(EBM.getMsgBasicInfo().getSenderName());
		nodeNews.setSenderCode(EBM.getMsgBasicInfo().getSenderCode());
		nodeNews.setSendTime(EBM.getMsgBasicInfo().getSendTime());
		nodeNews.setEventType(EBM.getMsgBasicInfo().getEventType());
		nodeNews.setSeverity(EBM.getMsgBasicInfo().getSeverity());
		nodeNews.setStartTime(EBM.getMsgBasicInfo().getStartTime());
		nodeNews.setEndTime(EBM.getMsgBasicInfo().getEndTime());
		nodeNews.setLanguageCode(EBM.getMsgContent().getLanguageCode());
		nodeNews.setMsgTitle(EBM.getMsgContent().getMsgTitle());
		nodeNews.setMsgDesc(EBM.getMsgContent().getMsgDesc());
		nodeNews.setAreaCode(EBM.getMsgContent().getAreaCode());
		EBD_EBM.Auxiliary Auxiliary = EBM.getMsgContent().getAuxiliary();
		if(Auxiliary!=null) {
			nodeNews.setAuxiliaryType(Auxiliary.getAuxiliaryType());
			nodeNews.setAuxiliaryDesc(Auxiliary.getAuxiliaryDesc());
			nodeNews.setSize(Auxiliary.getSize());
		}
		return nodeNews;
	}
}
