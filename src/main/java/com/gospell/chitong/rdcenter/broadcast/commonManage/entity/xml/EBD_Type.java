/**   
* @Title: EBD_Type.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月14日 下午5:08:04 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

/** 
* @ClassName: EBD_Type 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年12月14日 下午5:08:04 
*  
*/
public class EBD_Type {
	public static Class<? extends EBD> getClassByEBDType(String EBDType) {
		switch (EBDType) {
		case "ConnectionCheck":
			return  EBD_ConnectionCheck.class;
		case "EBD":
			return  EBD_EBD.class;
		case "EBMBrdLog":
			return EBD_EBMBrdLog.class;
		case "EBM":
			return  EBD_EBMStateRequest.class;
		case "EBMStateRequest":
			return  EBD_EBMStateResponse.class;
		case "EBRASInfo":
			return  EBD_EBRASInfo.class;
		case "EBRBSInfo":
			return  EBD_EBRBSInfo.class;
		case "EBRPSInfo":
			return  EBD_EBRPSInfo.class;
		case "EBRPSState":
			return  EBD_EBRPSState.class;
		default:
			return EBD_Signature.class;
		}
	}
}
