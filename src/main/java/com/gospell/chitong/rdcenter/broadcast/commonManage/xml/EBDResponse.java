package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;

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
public class EBDResponse extends BaseXML{
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
	 * 根据实体类生成回执实体
	 * @Title: getResponseByClass 
	 * @Description: TODO(具体回执暂未处理，目前只写了统一回复方式) 
	 * @param @param entity
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月28日 上午8:45:18
	 */
	@Override
	public BaseXML getResponseByClass(BaseXML entity) {
		EBDResponse response = (EBDResponse) super.getResponseByClass(entity);
		response.setEBDResponse_ResultCode("1");
		response.setEBDResponse_ResultDesc("已完成接收");
		return response;
	}
}
