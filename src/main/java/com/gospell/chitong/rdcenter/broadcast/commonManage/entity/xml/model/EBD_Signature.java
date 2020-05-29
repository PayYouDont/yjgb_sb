/**   
* @Title: EBD_Signature.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:46:44 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.util.SignatureUtil;

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
		private String Version;
		private RelatedEBD RelatedEBD;
		private String CertSN;
		private String SignatureAlgorithm;
		private String SignatureValue;
	}

	public EBD_Signature() {
		
	}
	public EBD_Signature(String EBDID,byte[] inData) {
		Signature = new Signature();
		Signature.setVersion("1");
		Signature.RelatedEBD = new RelatedEBD();
		Signature.RelatedEBD.EBDID = EBDID;
		Signature.CertSN = SignatureUtil.getCertSN();
		Signature.SignatureValue = SignatureUtil.signature(inData);
		Signature.SignatureAlgorithm = "SM2-SM3";
	}
	
	@lombok.Data
	public static class RelatedEBD {
		private String EBDID;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD()
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
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#getEBD() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月19日 上午11:01:58
	 */
	@Override
	public BaseEBD getEBD() {
		return null;
	}
	
}
