/**   
* @Title: EBD_EBMStateResponse.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:59:02 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EBD_EBMStateResponse
 * @Description: TODO(播发状态回执,应急广播消息播发状态反馈文件)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:59:02
 * 
 */
@lombok.Data
public class EBD_EBMStateResponse implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBMStateResponse EBMStateResponse;
	}

	@lombok.Data
	public static class EBMStateResponse {
		private String RptTime;
		private EBM EBM;
		/**
		 *：未处理 1：等待播发，指未到消息播发时间 2：播发中 3：播发成功 4：播发失败 5:播发取消
		 */
		private String BrdStateCode;
		private String BrdStateDesc;
		private Coverage Coverage;
		private ResBrdInfo ResBrdInfo;
	}
	
	@lombok.Data
	public static class EBM {
		private String EBMID;
	}

	@lombok.Data
	public static class Coverage {
		private String CoverageRate;
		private String AreaCode;
		private String ResBrdStat;
	}

	@lombok.Data
	public static class ResBrdInfo {
		private List<ResBrdItem> ResBrdItem;
	}

	@lombok.Data
	public static class ResBrdItem {
		private EBRPS EBRPS;
		private EBRST EBRST;
		private EBRAS EBRAS;
		private EBRBS EBRBS;
	}

	@lombok.Data
	public static class EBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRST {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRAS {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRBS {
		private String RptTime;
		private String BrdSysInfo;
		private String StartTime;
		private String EndTime;
		private String FileURL;
		private String BrdStateCode;
		private String BrdStateDesc;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p>
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:28
	 */
	@Override
	public EBD_EBMStateResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	public EBD_EBMStateResponse(){
		EBD = new EBD_EBMStateResponse.EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("EBMStateResponse");
		EBD.EBMStateResponse = new EBMStateResponse();
		EBD.EBMStateResponse.RptTime = DateUtils.getDateTime();
	}
	public EBD_EBMStateResponse(Emergencyinfo emergencyinfo,String RelatedEBDID){
		EBD = new EBD_EBMStateResponse.EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("EBMStateResponse");
		if (RelatedEBDID!=null){
			BaseEBD.RelatedEBD relatedEBD = new BaseEBD.RelatedEBD();
			relatedEBD.setEBDID(RelatedEBDID);
			EBD.setRelatedEBD(relatedEBD);
		}
		EBD.EBMStateResponse = new EBMStateResponse();
		EBD.EBMStateResponse.RptTime = DateUtils.getDateTime();
		EBD.EBMStateResponse.EBM = new EBM();
		EBD.EBMStateResponse.EBM.EBMID = emergencyinfo.getEbmId();
		EBD.EBMStateResponse.BrdStateCode = String.valueOf(emergencyinfo.getStatusToEBM());
		EBD.EBMStateResponse.BrdStateDesc = emergencyinfo.getStatusDsc();
		EBD.EBMStateResponse.ResBrdInfo = new ResBrdInfo();
		/*List<ResBrdItem> resBrdItemList = new ArrayList<>();
		ResBrdItem resBrdItem = new ResBrdItem();
		resBrdItem.EBRPS = new EBRPS();
		resBrdItem.EBRPS.EBRID = ApplicationContextRegister.getBean(ServerProperties.class).getSRC_EBRID();
		resBrdItem.EBRBS = new EBRBS();
		resBrdItem.EBRBS.RptTime = DateUtils.getDateTime();
		resBrdItem.EBRBS.BrdSysInfo = "(23400000000000301010301,3,97400)";
		resBrdItem.EBRBS.StartTime = DateUtils.formatDateTime(emergencyinfo.getStartTime());
		resBrdItem.EBRBS.EndTime = DateUtils.formatDateTime(emergencyinfo.getEndTime());
		resBrdItem.EBRBS.FileURL = "http://192.168.100.108:8090/a.mp3";
		resBrdItem.EBRBS.BrdStateCode = String.valueOf(emergencyinfo.getStatusToEBM());
		resBrdItem.EBRBS.BrdStateDesc = emergencyinfo.getStatusDsc();
		resBrdItemList.add(resBrdItem);
		EBD.EBMStateResponse.ResBrdInfo.ResBrdItem = resBrdItemList;*/
	}
}
