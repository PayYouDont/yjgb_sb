/**   
* @Title: EBD_EBRPSState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午6:01:44 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.state;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EBD_EBRPSState
 * @Description: TODO(应急广播平台状态文件)
 * @author peiyongdong
 * @date 2018年12月13日 下午6:01:44
 * 
 */
@lombok.Data
public class EBD_EBRPSState implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRPSState EBRPSState;
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
	public static class EBRPSState {
		private List<EBRPS> EBRPS;
	}

	@lombok.Data
	public static class EBRPS {
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
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p>
	 * @return
	 * @throws
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:36:46
	 */
	@Override
	public EBD_EBRPSState creatResponse() {
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {
	    EBD = new EBD ();
	    EBD.setEBDHeader ();
	    EBD.setEBDType ("EBRPSState");
        EBD.EBRPSState = new EBRPSState ();
        EBD.EBRPSState.EBRPS = new ArrayList<> ();
        EBRPS ebrps = new EBRPS ();
        ebrps.setRptTime (DateUtils.getDateTime ());
        ServerProperties properties = ApplicationContextRegister.getBean (ServerProperties.class);
        ebrps.setEBRID (EBDcodeUtil.getEBRID(properties.getAreaCode ()));
        ebrps.setStateCode ("1");
        ebrps.setStateDesc ("开机/运行正常");
        EBD.EBRPSState.EBRPS.add (ebrps);
        return this;
    }
    @Override
    public EBDResponse createIncrementalResponse() {
        EBD = new EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBRPSState");
        EBD.EBRPSState = new EBRPSState ();
        EBD.EBRPSState.EBRPS = new ArrayList<> ();
        return this;
    }
}
