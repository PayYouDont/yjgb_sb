/**   
* @Title: EBD_ConnectionCheck.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午4:47:41 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBD_ConnectionCheck 
* @Description: TODO(心跳检测) 
* @author peiyongdong
* @date 2018年12月13日 下午4:47:41 
*  
*/
@lombok.Data
public class EBD_ConnectionCheck implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private ConnectionCheck ConnectionCheck;
	}
	@lombok.Data
	public static class ConnectionCheck {
		private String RptTime;
	}
	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:19:53
	 */
	@Override
	public EBD_ConnectionCheck creatResponse() {
		EBD.setEBDHeader();
		EBD.setEBDType("ConnectionCheck");
		EBD.ConnectionCheck = new ConnectionCheck();
		EBD.ConnectionCheck.RptTime = DateUtils.getDateTime();
		EBD_ConnectionCheck check = new EBD_ConnectionCheck();
		check.EBD = EBD;
		return check;
	}
}
