/*
package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

*//** 
* @ClassName: Signature 
* @Description: TODO(应急广播消息签名文件) 
* @author peiyongdong
* @date 2018年11月20日 上午11:50:15 
*  
*//*
@Data
public class Signature {
	
	private String Signature_Version = "1";
	private String RelatedEBD_EBDID;
	private String CertSN;
	private String Signature_SignatureAlgorithm = "SM2-SM3";
	private String Signature_SignatureValue;
	
	public Map<String, Object> getMap() {
		Map<String,Object> root = new LinkedHashMap<>();
		Map<String,Object> RelatedEBD = new LinkedHashMap<>();
		RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
		root.put("RelatedEBD",RelatedEBD);
		root.put("Version", getSignature_Version());
		root.put("CertSN",getCertSN());
		root.put("SignatureAlgorithm",getSignature_SignatureAlgorithm());
		root.put("SignatureValue",getSignature_SignatureValue());
		return root;
	}
}
*/