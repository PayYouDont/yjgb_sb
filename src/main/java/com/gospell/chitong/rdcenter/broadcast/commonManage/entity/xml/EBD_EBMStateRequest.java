/**   
* @Title: EBD_EBMStateRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:58:26 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBMStateRequest
 * @Description: TODO(播发状态请求)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:58:26
 * 
 */
@lombok.Data
public class EBD_EBMStateRequest implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBMStateRequest EBMStateRequest;
	}
	
	@lombok.Data
	public static class EBMStateRequest {
		private String EBMID;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:16
	 */
	@Override
	public EBD_EBMStateRequest creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
