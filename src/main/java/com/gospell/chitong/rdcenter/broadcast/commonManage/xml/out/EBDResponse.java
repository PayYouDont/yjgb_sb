package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 播发接收状态回执
* @ClassName: EBDResponse 
* @Description: TODO(  播发接收状态回执   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:15:27 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBDResponse extends BaseXML implements ResponseXML{
	private String RelatedEBD_EBDID;
	private String EBDResponse_ResultCode;
	private String EBDResponse_ResultDesc;
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		if( getRelatedEBD_EBDID()!=null) {
			Map<String,Object> RelatedEBD = new LinkedHashMap<>();
			RelatedEBD.put("EBDID", getRelatedEBD_EBDID());
			root.put("RelatedEBD", RelatedEBD);
		}
		Map<String,Object> EBDResponse = new LinkedHashMap<>();
		EBDResponse.put("ResultCode", getEBDResponse_ResultCode());
		EBDResponse.put("ResultDesc", getEBDResponse_ResultDesc());
		root.put("EBDResponse", EBDResponse);
		return root;
	}
	/** 
	 * <p>Title: getResultCode</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultCode() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:34:39
	 */
	@Override
	public String getResultCode() {
		// TODO Auto-generated method stub
		return ResponseXML.super.getResultCode();
	}
	/** 
	 * <p>Title: getResultDesc</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultDesc() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:34:46
	 */
	@Override
	public String getResultDesc() {
		// TODO Auto-generated method stub
		return ResponseXML.super.getResultDesc();
	}
}
