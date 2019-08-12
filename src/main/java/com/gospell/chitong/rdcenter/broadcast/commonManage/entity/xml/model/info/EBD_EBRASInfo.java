/**   
* @Title: EBD_EBRASInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:51:37 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EBD_EBRASInfo
 * @Description: TODO(应急广播适配器信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:51:37
 * 
 */
@lombok.Data
public class EBD_EBRASInfo implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRASInfo EBRASInfo;
	}

	@lombok.Data
	public static class SRC {
		private String EBRID;
	}

	@lombok.Data
	public static class DEST {
		private String EBRID;
	}
	
	@lombok.Data
	public static class EBRASInfo {
		private Params Params;
		private List<EBRAS> EBRAS;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRAS {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private RelatedEBRST RelatedEBRST;
		private String EBRID;
		private String EBRName;
		private String Longitude;
		private String Latitude;
		private String URL;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRST {
		private String EBRID;
	}

	@Override
	public EBD_EBRASInfo creatResponse() {
		return createFullResponse();
	}

	@Override
    public EBD_EBRASInfo createFullResponse() {
		EBD = new EBD_EBRASInfo.EBD ();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRASInfo");
		EBD.EBRASInfo = new EBD_EBRASInfo.EBRASInfo();
		EBD.EBRASInfo.EBRAS = new ArrayList<>();
		List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("适配设备");
		//ServerProperties serverProperties = ApplicationContextRegister.getBean(ServerProperties.class);
		deviceInfos.forEach (deviceInfo -> {
			EBD_EBRASInfo.EBRAS ebras = new EBD_EBRASInfo.EBRAS();
			ebras.setRptTime (DateUtils.getDateTime ());
			//ebras.setEBRID (deviceInfo.getResouceCode ());
			ebras.setEBRID ("43415230000000103010201");
			ebras.setEBRName(deviceInfo.getDevname());
			ebras.setLatitude(deviceInfo.getLat());
			ebras.setLongitude(deviceInfo.getLng());
			ebras.setRptType("Full");
			ebras.setURL(" ");
			EBD.EBRASInfo.EBRAS.add (ebras);
		});
        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
		EBD = new EBD_EBRASInfo.EBD ();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRASInfo");
		EBD.EBRASInfo = new EBD_EBRASInfo.EBRASInfo();
		EBD.EBRASInfo.EBRAS = new ArrayList<>();
        return this;
    }
}
