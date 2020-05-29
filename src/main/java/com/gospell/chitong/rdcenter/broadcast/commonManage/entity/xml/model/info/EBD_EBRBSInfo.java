/**   
* @Title: EBD_EBRBSInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:55:17 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EBD_EBRBSInfo
 * @Description: TODO(传输覆盖播出设备信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:55:17
 * 
 */
@lombok.Data
public class EBD_EBRBSInfo implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRBSInfo EBRBSInfo;
	}

	@lombok.Data
	public static class EBRBSInfo {
		private Params Params;
		private List<EBRBS> EBRBS;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRBS {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private RelatedEBRST RelatedEBRST;
		private RelatedEBRAS RelatedEBRAS;
		private String EBRID;
		private String EBRName;
		private String Longitude;
		private String Latitude;
		private String URL;
		private String Square;
		private String AreaCode;
		private String Population;
		private String LanguageCode;
		private String EquipRoom;
		private RadioParams RadioParams;
		private TVParams TVParams;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRST {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRAS {
		private String EBRID;
	}

	@lombok.Data
	public static class RadioParams {
		private String ChannelName;
		private String Freq;
		private String Power;
		private String Backup;
		private String AutoSwitch;
		private String RemoteControl;
	}

	@lombok.Data
	public static class TVParams {
		private String ChannelName;
		private String Freq;
		private String ProgramNum;
		private String ChannelNum;
	}
    @Override
    public EBD_EBRBSInfo createFullResponse() {
		EBD = new EBD_EBRBSInfo.EBD();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRDTInfo");
		EBD.EBRBSInfo = new EBRBSInfo();
		EBD.EBRBSInfo.Params = new Params();
		EBD.EBRBSInfo.Params.RptStartTime = DateUtils.getDateTime();
		EBD.EBRBSInfo.Params.RptEndTime = DateUtils.getDateTime();
		EBD.EBRBSInfo.Params.RptType = "Full";
		EBD.EBRBSInfo.EBRBS = new ArrayList<>();
		List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("接收终端");
		deviceInfos.forEach(deviceInfo -> EBD.EBRBSInfo.EBRBS.add(parseEBRDT(deviceInfo)));
		return this;
    }

    @Override
    public EBD_EBRBSInfo createIncrementalResponse() {
        return null;
    }
	private static EBRBS parseEBRDT(Deviceinfo deviceInfo){
		EBRBS ebrbs = new EBRBS();
		ebrbs.RptTime = DateUtils.getDateTime ();
		ebrbs.RptType = "Sync";
		ebrbs.EBRID = deviceInfo.getResouceCode ("EBRBS");
		ebrbs.EBRName = deviceInfo.getDevname ();
		ebrbs.Longitude = deviceInfo.getLng ();
		ebrbs.Latitude = deviceInfo.getLat ();
		//ebrbs.URL = "http://192.168.0.100:8090";
		ebrbs.Square = "120km";
		ebrbs.AreaCode = deviceInfo.getDevaddresscode();
		ebrbs.Population = "13.2";
		ebrbs.LanguageCode = "zho";
		ebrbs.EquipRoom = "舒城县调频发射机机房";
		return  ebrbs;
	}
}
