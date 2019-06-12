/**   
* @Title: EBD_EBRDTState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:52:45 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.state;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

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
		private EBRDT EBRDT;
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
    public EBDResponse createFullResponse() {
        return null;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
    public static EBD_EBRDTState createInstance(Deviceinfo deviceinfo){
        EBD_EBRDTState state = new EBD_EBRDTState ();
        state.EBD = new EBD ();
        state.EBD.setEBDHeader ();
        state.EBD.setEBDType ("EBRDTState");
        state.EBD.EBRDTState = new EBRDTState ();
        state.EBD.EBRDTState.EBRDT = new EBRDT ();
        state.EBD.EBRDTState.EBRDT.RptTime = DateUtils.getDateTime ();
        state.EBD.EBRDTState.EBRDT.EBRID = deviceinfo.getResouceCode ();
        state.EBD.EBRDTState.EBRDT.StateCode = deviceinfo.getStatus ();
        state.EBD.EBRDTState.EBRDT.StateDesc = deviceinfo.getStatusDesc ();
        return state;
    }
}
