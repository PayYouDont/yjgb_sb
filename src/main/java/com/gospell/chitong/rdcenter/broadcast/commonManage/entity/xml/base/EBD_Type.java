/**
* @Title: EBD_Type.java
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml
* @Description: TODO(     )
* @author peiyongdong
* @date 2018年12月14日 下午5:08:04
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.*;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info.*;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRASState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRBSState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRDTState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRPSState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.request.EBD_EBMStateRequest;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.request.EBD_OMDRequest;

/**
* @ClassName: EBD_Type
* @Description: TODO(     )
* @author peiyongdong
* @date 2018年12月14日 下午5:08:04
*
*/
public class EBD_Type {
	/*
	 * 1：开机/运行正常
	 * 2：关机/停止运行
	 * 3：故障
	 * 4：故障恢复
	 * 5：播发中
	 */
	public static final String State_Normal = "1";
	public static final String State_Stop = "2";
	public static final String State_Error = "3";
	public static final String State_Recovery = "4";
	public static final String State_Running = "5";
	public static Class<? extends EBD> getClassByEBDType(String EBDType) {
		switch (EBDType) {
		case "EBM":
			//应急广播
			return  EBD_EBM.class;
		case "OMDRequest":
			//运维数据请求文件
			return EBD_OMDRequest.class;
		case "EBMStateRequest":
			//应急广播消息播发状态查询文件
			return  EBD_EBMStateRequest.class;
		case "EBMStateResponse":
			//应急广播消息播发状态反馈文件
			return  EBD_EBMStateResponse.class;
		case "EBMBrdLog":
			//播发记录
			return EBD_EBMBrdLog.class;
		case "EBDResponse":
			//接收回执文件
			return  EBD_EBDResponse.class;
		case "EBRPSInfo":
			//应急广播平台信息
			return  EBD_EBRPSInfo.class;
		case "EBRSTInfo":
			//台站（前端）信息
			return EBD_EBRSTInfo.class;
		case "EBRASInfo":
			//应急广播适配器信息
			return  EBD_EBRASInfo.class;
		case "EBRBSInfo":
			//传输覆盖播出设备信息
			return  EBD_EBRBSInfo.class;
		case "EBRDTInfo":
			//平台设备及终端信息；
			return EBD_EBRDTInfo.class;
		case "EBRPSState":
			//应急广播平台状态文件
			return  EBD_EBRPSState.class;
		case "EBRASState":
			//应急广播适配器状态
			return EBD_EBRASState.class;
		case "EBRBSState":
            //：传输覆盖播出设备状态
			return EBD_EBRBSState.class;
		case "EBRDTState":
			//平台设备及终端状态
			return EBD_EBRDTState.class;
		case "ConnectionCheck":
			//心跳
			return EBD_ConnectionCheck.class;
		default :
			//签名文件
			return EBD_Signature.class;
		}
	}
}
