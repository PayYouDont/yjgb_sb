/**   
* @Title: EBD_EBRPSInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:49:55 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBRPSInfo
 * @Description: TODO(平台信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:49:55
 * 
 */
@lombok.Data
public class EBD_EBRPSInfo implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRPSInfo EBRPSInfo;
	}
	
	@lombok.Data
	public static class EBRPSInfo {
		private Params Params;
		private EBRPS EBRPS;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRPS {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private String EBRID;
		private String EBRName;
		private String Address;
		private String Contact;
		private String PhoneNumber;
		private String Longitude;
		private String Latitude;
		private String URL;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:36:37
	 */
	@Override
	public EBD_EBRPSInfo creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EBD_EBRPSInfo() {
		
	}
	public EBD_EBRPSInfo(ServerProperties prop) {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("EBRPSInfo");
		EBD.EBRPSInfo = new EBRPSInfo();
		EBD.EBRPSInfo.EBRPS = new EBRPS();
		EBD.EBRPSInfo.EBRPS.RptTime = DateUtils.getDateTime();
		EBD.EBRPSInfo.EBRPS.RptType = "Sync";
		EBD.EBRPSInfo.EBRPS.EBRID = prop.getDEST_EBRID();
		EBD.EBRPSInfo.EBRPS.EBRID = prop.getSRC_EBRID();
		EBD.EBRPSInfo.EBRPS.EBRName = prop.getManageName();
		EBD.EBRPSInfo.EBRPS.Address = prop.getUnitName();
		EBD.EBRPSInfo.EBRPS.Contact ="管理员";
		EBD.EBRPSInfo.EBRPS.PhoneNumber = "15111111111";
		EBD.EBRPSInfo.EBRPS.Longitude = prop.getAreaLongitude();
		EBD.EBRPSInfo.EBRPS.Latitude = prop.getAreaLatitude();
		EBD.EBRPSInfo.EBRPS.URL = prop.getServer_ip()+prop.getServer_port()+"/nodeAction/upload";
	}
}
