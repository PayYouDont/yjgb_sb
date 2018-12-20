/**   
* @Title: EBD_Signature.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:46:44 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml;

/**
 * @ClassName: EBD_Signature
 * @Description: TODO(签名)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:46:44
 * 
 */
@lombok.Data
public class EBD_Signature implements EBD{

	private Signature Signature;

	@lombok.Data
	public static class Signature {
		private RelatedEBD RelatedEBD;
		private String Version;
		private String CertSN;
		private String SignatureAlgorithm;
		private String SignatureValue;
	}

	@lombok.Data
	public static class RelatedEBD {
		private String EBDID;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:37:00
	 */
	@Override
	public EBD_Signature creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * <p>Title: getEBD</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD#getEBD() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月19日 上午11:01:58
	 */
	@Override
	public BaseEBD getEBD() {
		return null;
	}
	
}
