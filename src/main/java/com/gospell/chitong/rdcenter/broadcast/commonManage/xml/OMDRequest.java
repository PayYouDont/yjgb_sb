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
}
