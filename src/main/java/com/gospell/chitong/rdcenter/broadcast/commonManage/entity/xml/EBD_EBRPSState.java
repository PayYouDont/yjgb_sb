/**   
* @Title: EBD_EBRPSState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午6:01:44 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBRPSState
 * @Description: TODO(平台状态信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午6:01:44
 * 
 */
@lombok.Data
public class EBD_EBRPSState implements EBD{

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
		private EBRPS EBRPS;
	}

	@lombok.Data
	public static class EBRPS {
		private String RptTime;
		private String StateCode;
		private String StateDesc;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:36:46
	 */
	@Override
	public EBD_EBRPSState creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
