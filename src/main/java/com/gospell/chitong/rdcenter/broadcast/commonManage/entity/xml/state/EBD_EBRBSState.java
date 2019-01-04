/**   
* @Title: EBD_EBRBSState.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午11:31:15 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.state;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;

/** 
* @ClassName: EBD_EBRBSState 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 上午11:31:15 
*  
*/

@lombok.Data
public class EBD_EBRBSState implements EBD{
	
	private EBD EBD;
	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRBSState EBRBSState;
	}
	@lombok.Data
	public static class EBRBSState {
		private EBRBS EBRBS;
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

}
