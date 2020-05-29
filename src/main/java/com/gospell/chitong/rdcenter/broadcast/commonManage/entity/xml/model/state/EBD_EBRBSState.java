/**   
* @Title: EBD_EBRBSState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午11:31:15 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

/** 
* @ClassName: EBD_EBRBSState 
* @Description: TODO(传输覆盖播出设备状态)
* @author peiyongdong
* @date 2019年1月3日 上午11:31:15 
*  
*/

@lombok.Data
public class EBD_EBRBSState implements EBDResponse {
	
	private EBD EBD;
	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRBSState EBRBSState;
	}
	@lombok.Data
	public static class EBRBSState {
		private List<EBRBS> EBRBS;
	}
	@lombok.Data
	public static class EBRBS {
		private String RptTime;
		private String EBRID;
		/*
		 * 1：开机/运行正常
		 * 2：关机/停止运行
		 * 3：故障
		 * 4：故障恢复
		 * 5：播发中
		 */
		private String StateCode;
		private String StateDesc;
	}
	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponse() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 上午11:37:33
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBD_EBRBSState createFullResponse() {
		EBD = new EBD_EBRBSState.EBD();
		EBD.setEBDHeader();
		EBD.setEBDType ("EBRBSState");
		EBD.EBRBSState = new EBRBSState();
		EBD.EBRBSState.EBRBS = new ArrayList<>();
		List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("接收终端");
		deviceInfos.forEach (deviceInfo -> {
			EBRBS ebras = new EBRBS();
			ebras.setRptTime (DateUtils.getDateTime ());
			ebras.setEBRID (deviceInfo.getResouceCode ("EBRBS"));
			ebras.setStateCode (deviceInfo.getStatus ().toString ());
			ebras.setStateDesc (deviceInfo.getStatusDesc ());
			EBD.EBRBSState.EBRBS.add (ebras);
		});
        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
}
