/**   
* @Title: EBD_EBRDTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:35:02 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

/** 
* @ClassName: EBD_EBRDTInfo 
* @Description: TODO(平台设备及终端信息)
* @author peiyongdong
* @date 2019年1月3日 上午10:35:02 
*  
*/
@lombok.Data
public class EBD_EBRDTInfo implements EBDResponse {
	
	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRDTInfo EBRDTInfo;
	}

	@lombok.Data
	public static class EBRDTInfo {
		private EBRDT EBRDT;
	}

	@lombok.Data
	public static class EBRDT {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private String EBRID;
		private String EBRName;
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
	 * @date 2019年1月3日 上午10:36:35
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {
        return null;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
    public static EBD_EBRDTInfo createInstance(Deviceinfo deviceinfo){
        EBD_EBRDTInfo info = new EBD_EBRDTInfo ();
        info.EBD = new EBD ();
        info.EBD.setEBDHeader ();
        info.EBD.setEBDType ("EBRDTInfo");
        info.EBD.EBRDTInfo = new EBRDTInfo ();
        info.EBD.EBRDTInfo.EBRDT = new EBRDT ();
        info.EBD.EBRDTInfo.EBRDT.RptTime = DateUtils.getDateTime ();
        info.EBD.EBRDTInfo.EBRDT.RptType = "Sync";
        info.EBD.EBRDTInfo.EBRDT.RelatedEBRPS = new RelatedEBRPS ();
        info.EBD.EBRDTInfo.EBRDT.RelatedEBRPS.EBRID = ApplicationContextRegister.getBean (ServerProperties.class).getSRC_EBRID ();
        info.EBD.EBRDTInfo.EBRDT.EBRID = deviceinfo.getResouceCode ();
        info.EBD.EBRDTInfo.EBRDT.EBRName = deviceinfo.getDevname ();
        info.EBD.EBRDTInfo.EBRDT.Longitude = deviceinfo.getLng ();
        info.EBD.EBRDTInfo.EBRDT.Latitude = deviceinfo.getLat ();
	    return info;
    }
}
