/**   
* @Title: EBD_EBRDTState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:52:45 
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
* @ClassName: EBD_EBRDTState 
* @Description: TODO(平台设备及终端状态)
* @author peiyongdong
* @date 2019年1月3日 上午10:52:45 
*  
*/
@lombok.Data
public class EBD_EBRDTState implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRDTState EBRDTState;
	}

	@lombok.Data
	public static class EBRDTState {
		private List<EBRDT> EBRDT;
	}

	@lombok.Data
	public static class EBRDT {
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
	 * @date 2019年1月3日 上午10:54:42
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBD_EBRDTState createFullResponse() {
		EBD = new EBD_EBRDTState.EBD();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRDTState");
		EBD.EBRDTState = new EBD_EBRDTState.EBRDTState();
		EBD.EBRDTState.EBRDT = new ArrayList<>();
		List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("接收终端");
		deviceInfos.forEach(deviceInfo -> EBD.EBRDTState.EBRDT.add(parseEBRDT(deviceInfo)));
		return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
		EBD = new EBD_EBRDTState.EBD();
		EBD.setEBDHeader ();
		EBD.setEBDType ("EBRDTState");
		EBD.EBRDTState = new EBD_EBRDTState.EBRDTState();
		EBD.EBRDTState.EBRDT = new ArrayList<>();
        return this;
    }
    public static EBD_EBRDTState createInstance(Deviceinfo deviceinfo){
        EBD_EBRDTState state = new EBD_EBRDTState ();
        state.EBD = new EBD ();
        state.EBD.setEBDHeader ();
        state.EBD.setEBDType ("EBRDTState");
        state.EBD.EBRDTState = new EBRDTState ();
        state.EBD.EBRDTState.EBRDT = new ArrayList<>();
		state.EBD.EBRDTState.EBRDT.add(parseEBRDT(deviceinfo));
        return state;
    }
    private static EBRDT parseEBRDT(Deviceinfo deviceinfo){
		EBRDT ebrdt = new EBRDT();
		ebrdt.RptTime = DateUtils.getDateTime ();
		ebrdt.EBRID = deviceinfo.getResouceCode ();
		ebrdt.StateCode = String.valueOf(deviceinfo.getStatusToEBD ());
		ebrdt.StateDesc = deviceinfo.getStatusDesc ();
		return ebrdt;
	}
}
