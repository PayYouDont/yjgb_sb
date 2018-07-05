package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBMStateRequest extends BaseXML{
	private String EBM_EBMID;
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		Map<String,Object> EBMStateRequest = new LinkedHashMap<>();
		Map<String,Object> EBM = new LinkedHashMap<>();
		EBM.put("EBMID", getEBM_EBMID());
		EBMStateRequest.put("EBM", EBM);
		root.put("EBMStateRequest", EBMStateRequest);
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
