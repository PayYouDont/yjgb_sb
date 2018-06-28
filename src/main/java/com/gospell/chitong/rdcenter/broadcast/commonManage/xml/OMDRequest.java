package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OMDRequest extends BaseXML{
	private String OMDRequest_OMDType;
	private String Params_RptStartTime;
	private String Params_RptEndTime;
	private String Params_RptType;
	
	public Map<String,Object> getParamsMap(){
		Map<String,Object> Params = new LinkedHashMap<>();
		Params.put("RptStartTime", getParams_RptStartTime());
		Params.put("RptEndTime", getParams_RptEndTime());
		Params.put("RptType", getParams_RptType());
		return Params;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		Map<String,Object> OMDRequest = new LinkedHashMap<>();
		OMDRequest.put("OMDType", getOMDRequest_OMDType());
		OMDRequest.put("Params", getParamsMap());
		root.put("OMDRequest", OMDRequest);
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
