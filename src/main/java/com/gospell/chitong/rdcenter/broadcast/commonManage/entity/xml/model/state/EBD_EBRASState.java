/**   
* @Title: EBD_EBRASState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:49:33 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.LoggerUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: EBD_EBRASState 
* @Description: TODO(应急广播适配器状态)
* @author peiyongdong
* @date 2019年1月3日 上午10:49:33 
*  
*/
@lombok.Data
public class EBD_EBRASState implements EBDResponse {
	
	private EBD EBD;
	
	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRASState EBRASState;
	}

	@lombok.Data
	public static class EBRASState {
		private EBRAS EBRAS;
	}

	@lombok.Data
	public static class EBRAS {
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
	 * @date 2019年1月3日 上午10:49:33
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
        //存入数据库
        Deviceinfo deviceinfo = new Deviceinfo();
        EBRAS ebras = getEBD().getEBRASState().getEBRAS();
        if (ebras!=null){
            deviceinfo.setDevdsn(ebras.getEBRID());
            String ebrid = ebras.getEBRID();
            Map<String,Object> map = new HashMap<>();
            map.put("devdsn",deviceinfo.getDevdsn());
            DeviceInfoService deviceInfoService = ApplicationContextRegister.getBean(DeviceInfoService.class);
            List<Deviceinfo> deviceinfoList = deviceInfoService.list(map);
            if (deviceinfoList!=null&&deviceinfoList.size()>0){
                deviceinfo = deviceinfoList.get(0);
            }
            String areaCode = EBDcodeUtil.getAreaCode(ebrid);
            deviceinfo.setDevaddresscode(areaCode);
            deviceinfo.setOnregister("1");
            deviceinfo.setStatus(Integer.parseInt(ebras.getStateCode()));
            deviceinfo.setStatusDesc(ebras.getStateDesc());
            deviceinfo.setDevname("高斯贝尔应急广播适配器");
            map = new HashMap<>();
            map.put("code",areaCode);
            List<Administrative> administrativeList = ApplicationContextRegister.getBean(AdministrativeMapper.class).list(map);
            if (administrativeList!=null&&administrativeList.size()>0){
                Administrative administrative = administrativeList.get(0);
                deviceinfo.setLat(administrative.getLatitude());
                deviceinfo.setLng(administrative.getLongitude());
            }
            map = new HashMap<>();
            map.put("devicetypeId",7);//适配器
            Devicemodel devicemodel = ApplicationContextRegister.getBean(DeviceModelService.class).list(map).get(0);
            deviceinfo.setDeviceModel(devicemodel);
            try {
                deviceInfoService.save(deviceinfo);
            }catch (Exception e){
                LoggerUtil.log(this.getClass(),e);
            }
        }
        //主动上报
        try {
            EBD_EBRASState state =createInstance (deviceinfo);
            TarUtil.sendEBDToSuperior(state);
        }catch (Exception e){
            LoggerUtil.log(this.getClass(),e);
        }
        //回复接收结果
        EBD_EBDResponse ebdEbdResponse = new EBD_EBDResponse();
        EBD_EBDResponse.EBD EBD = new EBD_EBDResponse.EBD();
        EBD.setEBDHeader();
        EBD.setEBDType("EBDResponse");
        EBD_EBDResponse.EBDResponse response = new EBD_EBDResponse.EBDResponse();
        response.setResultCode("1");
        response.setResultDesc("接收并解析成功");
        EBD.setEBDResponse(response);
        ebdEbdResponse.setEBD(EBD);
		return ebdEbdResponse;
	}

    @Override
    public EBD_EBRASState createFullResponse() {
        List<Deviceinfo> deviceInfos = ApplicationContextRegister.getBean(DeviceInfoService.class).getDeviceByType ("适配设备");
        if (deviceInfos!=null&&deviceInfos.size()>0){
            Deviceinfo deviceInfo = deviceInfos.get(0);
            return createInstance (deviceInfo);
        }
        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
	    return null;
	}
    public static EBD_EBRASState createInstance(Deviceinfo deviceInfo){
        EBD_EBRASState state = new EBD_EBRASState ();
        state.EBD = new EBD_EBRASState.EBD ();
        state.EBD.setEBDHeader ();
        state.EBD.setEBDType ("EBRASState");
        state.EBD.EBRASState = new EBRASState ();
        state.EBD.EBRASState.EBRAS = new EBRAS ();
        state.EBD.EBRASState.EBRAS.setRptTime (DateUtils.getDateTime ());
        //EBD.EBRASState.EBRAS.setEBRID("43415230000000103010201");
        state.EBD.EBRASState.EBRAS.setEBRID (deviceInfo.getResouceCode ("EBRAS"));
        state.EBD.EBRASState.EBRAS.setStateCode (deviceInfo.getStatus ().toString ());
        state.EBD.EBRASState.EBRAS.setStateDesc (deviceInfo.getStatusDesc ());
        return state;
    }
}
