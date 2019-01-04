/**   
* @Title: OMDRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:22:28 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.request;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

/** 
* @ClassName: OMDRequest 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 上午10:22:28 
*  
*/
@lombok.Data
public class EBD_OMDRequest implements EBD{

	public static final String RptType_Full = "Full";
	public static final String RptType_Incremental = "Incremental";
	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD  extends BaseEBD{
		private OMDRequest OMDRequest;
	}
	@lombok.Data
	public static class OMDRequest {
		private String OMDType;
		private Params Params;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponse() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:23:43
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EBD_OMDRequest() {
		super();
	}
	
	public EBD_OMDRequest(String OMDType,String RptType) {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("OMDRequest");
		EBD.OMDRequest = new OMDRequest();
		EBD.OMDRequest.OMDType = OMDType;
		EBD.OMDRequest.Params = new Params();
		EBD.OMDRequest.Params.RptStartTime = DateUtils.getDateTime();
		EBD.OMDRequest.Params.RptEndTime = DateUtils.getDateTime();
		EBD.OMDRequest.Params.RptType = RptType;
	}
}
