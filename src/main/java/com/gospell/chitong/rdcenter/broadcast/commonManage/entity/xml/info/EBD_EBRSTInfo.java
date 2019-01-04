/**   
* @Title: EBD_EBRSTInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 上午10:42:14 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

/** 
* @ClassName: EBD_EBRSTInfo 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 上午10:42:14 
*  
*/
@lombok.Data
public class EBD_EBRSTInfo implements EBD{

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRSTInfo EBRSTInfo;
	}

	@lombok.Data
	public static class EBRSTInfo {
		private Params Params;
		private EBRST EBRST;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRST {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private String EBRID;
		private String EBRName;
		private String Address;
		private String Contact;
		private String PhoneNumber;
		private String Longitude;
		private String Latitude;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	/** 
	 * <p>Title: creatResponse</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponse() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 上午10:43:07
	 */
	@Override
	public com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
