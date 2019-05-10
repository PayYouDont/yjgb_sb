/**   
* @Title: EBD_EBRSTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:42:14 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info;

import com.gospell.chitong.rdcenter.broadcast.Application;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

/** 
* @ClassName: EBD_EBRSTInfo 
* @Description: TODO(台站（前端）信息)
* @author peiyongdong
* @date 2019年1月3日 上午10:42:14 
*  
*/
@lombok.Data
public class EBD_EBRSTInfo implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRSTInfo EBRSTInfo;
	}

	@lombok.Data
	public static class EBRSTInfo {
		private Params Params;
		private EBRST EBRST;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRST {
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
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponse() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:43:07
	 */
	@Override
	public com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {
        setBaseResponse();
        EBD.EBRSTInfo.Params.RptType = "Full";
        EBD.EBRSTInfo.EBRST = new EBRST ();
        EBD.EBRSTInfo.EBRST.RptTime = DateUtils.getDateTime ();
        EBD.EBRSTInfo.EBRST.RptType = "Sync";
        EBD.EBRSTInfo.EBRST.RelatedEBRPS = new RelatedEBRPS ();
        //EBD.EBRSTInfo.EBRST.RelatedEBRPS.EBRID = EBDcodeUtil.getEBRID ();
        EBD.EBRSTInfo.EBRST.EBRID = EBD.getSRC ().getEBRID ();
        ServerProperties properties = ApplicationContextRegister.getBean (ServerProperties.class);
        EBD.EBRSTInfo.EBRST.EBRName = properties.getUnitName ();
        EBD.EBRSTInfo.EBRST.Address = "某某市某某街123号";
        EBD.EBRSTInfo.EBRST.Contact = "管理员";
        EBD.EBRSTInfo.EBRST.PhoneNumber = "15111111111";
        EBD.EBRSTInfo.EBRST.Longitude = properties.getAreaLongitude ();
        EBD.EBRSTInfo.EBRST.Latitude = properties.getAreaLatitude ();
        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        setBaseResponse();
        EBD.EBRSTInfo.Params.RptType = "Incremental";
        EBD.EBRSTInfo.EBRST = new EBRST ();
        return this;
    }
    private void setBaseResponse(){
        EBD = new EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRSTInfo");
        EBD.EBRSTInfo = new EBRSTInfo ();
        EBD.EBRSTInfo.Params = new Params ();
        EBD.EBRSTInfo.Params.RptStartTime = DateUtils.getDateTime ();
        EBD.EBRSTInfo.Params.RptEndTime = DateUtils.getDateTime ();
    }
}
