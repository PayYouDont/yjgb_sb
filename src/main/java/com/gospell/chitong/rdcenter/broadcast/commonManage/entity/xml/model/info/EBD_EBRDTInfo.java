/**   
* @Title: EBD_EBRDTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:35:02 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

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
		private List<EBRDT> EBRDT;
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
    @Override
    public EBDResponse createFullResponse() {
		EBD = new EBD_EBRDTInfo.EBD();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRDTInfo");
		EBD.EBRDTInfo = new EBRDTInfo();
		EBD.EBRDTInfo.EBRDT = new ArrayList<>();
		List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("接收终端");
		deviceInfos.forEach(deviceInfo -> EBD.EBRDTInfo.EBRDT.add(parseEBRDT(deviceInfo)));
		return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
		EBD = new EBD_EBRDTInfo.EBD();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRDTInfo");
		EBD.EBRDTInfo = new EBRDTInfo();
		EBD.EBRDTInfo.EBRDT = new ArrayList<>();
        return this;
    }
    public static EBD_EBRDTInfo createInstance(Deviceinfo deviceinfo){
        EBD_EBRDTInfo info = new EBD_EBRDTInfo ();
        info.EBD = new EBD ();
        info.EBD.setEBDHeader ();
        info.EBD.setEBDType ("EBRDTInfo");
        info.EBD.EBRDTInfo = new EBRDTInfo ();
        info.EBD.EBRDTInfo.EBRDT = new ArrayList ();
		info.EBD.EBRDTInfo.EBRDT.add(parseEBRDT(deviceinfo));
	    return info;
    }
    private static EBRDT parseEBRDT(Deviceinfo deviceInfo){
		EBRDT ebrdt = new EBRDT();
		ebrdt.RptTime = DateUtils.getDateTime ();
		ebrdt.RptType = "Sync";
		ebrdt.RelatedEBRPS = new RelatedEBRPS ();
		ebrdt.RelatedEBRPS.EBRID = ApplicationContextRegister.getBean (ServerProperties.class).getSRC_EBRID ();
		ebrdt.EBRID = deviceInfo.getResouceCode ("EBRDT");
		//ebrdt.EBRID = "63415231002000301010401";
		ebrdt.EBRName = deviceInfo.getDevname ();
		ebrdt.Longitude = deviceInfo.getLng ();
		ebrdt.Latitude = deviceInfo.getLat ();
		return  ebrdt;
	}
}
