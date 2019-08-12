/**   
* @Title: EBD_EBRSTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:42:14 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

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
    @Override
    public EBDResponse createFullResponse() {
        setBaseResponse();
		Platform platform = ApplicationContextRegister.getBean(PlatformService.class).selectById(1);
		return createInstance(platform);
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
    public EBD_EBRSTInfo createInstance(Platform platform){
		setBaseResponse();
		if (platform!=null){
			EBD.EBRSTInfo.Params.RptType = "Sync";
			EBD.EBRSTInfo.EBRST = new EBRST ();
			EBD.EBRSTInfo.EBRST.RptTime = DateUtils.getDateTime ();
			EBD.EBRSTInfo.EBRST.EBRID = EBD.getSRC ().getEBRID ();
			EBD.EBRSTInfo.EBRST.EBRName = platform.getName();
			EBD.EBRSTInfo.EBRST.Address = platform.getAddress();
			EBD.EBRSTInfo.EBRST.Contact = platform.getContact();
			EBD.EBRSTInfo.EBRST.PhoneNumber = platform.getPhoneNumber();
			EBD.EBRSTInfo.EBRST.Longitude =platform.getLng();
			EBD.EBRSTInfo.EBRST.Latitude = platform.getLat();
		}
		return this;
	}
}
