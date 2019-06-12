/**   
* @Title: EBD_EBRASState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:49:33 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.state;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import java.util.ArrayList;
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
		private List<EBRAS> EBRAS;
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
	public EBD_EBRASState creatResponse() {
        EBD = new EBD_EBRASState.EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRASState");
        EBD.EBRASState = new EBRASState ();
        EBD.EBRASState.EBRAS = new ArrayList<> ();
		return this;
	}

    @Override
    public EBDResponse createFullResponse() {
        EBD = new EBD_EBRASState.EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRASState");
        EBD.EBRASState = new EBRASState ();
        EBD.EBRASState.EBRAS = new ArrayList<> ();
        List<Deviceinfo> deviceInfos = getDeviceByType ("适配设备");
        deviceInfos.forEach (deviceInfo -> {
            EBRAS ebras = new EBRAS ();
            ebras.setRptTime (DateUtils.getDateTime ());
            ebras.setEBRID (deviceInfo.getResouceCode ());
            //ebras.setStateCode (deviceInfo.getOnwork ());
            ebras.setStateDesc (deviceInfo.getStatusDesc ());
            EBD.EBRASState.EBRAS.add (ebras);
        });
        return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        EBD = new EBD_EBRASState.EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRASState");
        EBD.EBRASState = new EBRASState ();
        EBD.EBRASState.EBRAS = new ArrayList<> ();
        return this;
    }
    /**
    * @Author peiyongdong
    * @Description ( 根据devType获取设备 )
    * @Date 14:20 2019/5/15
    * @Param [devType]
    * @return java.util.List<com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo>
    **/
    private List<Deviceinfo> getDeviceByType(String devType){
        DevicetypeMapper dtDao = ApplicationContextRegister.getBean (DevicetypeMapper.class);
        Map<String,Object> map = new HashMap<> ();
        map.put ("devtype",devType);
        List<Devicetype> deviceTypes = dtDao.list (map);
        Devicetype devicetype = null;
        if(deviceTypes!=null&&deviceTypes.size ()>0){
            devicetype = deviceTypes.get (0);
        }
        Integer deviceTypeId = devicetype.getId ();
        DevicemodelMapper dmDao = ApplicationContextRegister.getBean (DevicemodelMapper.class);
        map = new HashMap<> ();
        map.put ("devicetypeId",deviceTypeId);
        List<Devicemodel> deviceModels = dmDao.list (map);
        DeviceinfoMapper diDao = ApplicationContextRegister.getBean (DeviceinfoMapper.class);
        List<Deviceinfo> list = new ArrayList<> ();
        deviceModels.forEach (deviceModel -> {
            Integer deviceModelId = deviceModel.getId ();
            Map<String,Object> objectMap = new HashMap<> ();
            objectMap.put ("devicemodelId",deviceModelId);
            List<Deviceinfo> deviceInfos = diDao.list (objectMap);
            list.addAll (deviceInfos);
        });
        return list;
    }
}
