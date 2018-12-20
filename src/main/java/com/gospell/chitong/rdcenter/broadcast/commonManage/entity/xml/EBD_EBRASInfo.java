/**   
* @Title: EBD_EBRASInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:51:37 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBRASInfo
 * @Description: TODO(适配器信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:51:37
 * 
 */
@lombok.Data
public class EBD_EBRASInfo implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRASInfo EBRASInfo;
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
	public static class EBRASInfo {
		private Params Params;
		private EBRAS EBRAS;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRAS {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private RelatedEBRST RelatedEBRST;
		private String EBRID;
		private String EBRName;
		private String Longitude;
		private String Latitude;
		private String URL;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRST {
		private String EBRID;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:44
	 */
	@Override
	public EBD_EBRASInfo creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
