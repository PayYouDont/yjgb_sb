/**   
* @Title: EBD_EBMStateResponse.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:59:02 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBMStateResponse
 * @Description: TODO(播发状态回执)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:59:02
 * 
 */
@lombok.Data
public class EBD_EBMStateResponse implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBMStateResponse EBMStateResponse;
	}

	@lombok.Data
	public static class EBMStateResponse {
		private String RptTime;
		private EBM EBM;
		private String BrdStateCode;
		private String BrdStateDesc;
		private Coverage Coverage;
		private ResBrdInfo ResBrdInfo;
	}
	
	@lombok.Data
	public static class EBM {
		private String EBMID;
	}

	@lombok.Data
	public static class Coverage {
		private String CoverageRate;
		private String AreaCode;
		private String ResBrdStat;
	}

	@lombok.Data
	public static class ResBrdInfo {
		private ResBrdItem ResBrdItem;
	}

	@lombok.Data
	public static class ResBrdItem {
		private EBRPS EBRPS;
		private EBRST EBRST;
		private EBRAS EBRAS;
		private EBRBS EBRBS;
	}

	@lombok.Data
	public static class EBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRST {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRAS {
		private String EBRID;
	}

	@lombok.Data
	public static class EBRBS {
		private String BrdSysInfo;
		private String StartTime;
		private String EndTime;
		private String FileURL;
		private String BrdStateCode;
		private String BrdStateDes;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:28
	 */
	@Override
	public EBD_EBMStateResponse creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
