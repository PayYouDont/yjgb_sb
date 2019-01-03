/*package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.RequestXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBMBrdLog;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRASInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRASState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRDTInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRDTState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRPSState;

import lombok.Data;
import lombok.EqualsAndHashCode;
*//**
 * 查询请求
* @ClassName: OMDRequest 
* @Description: TODO(  查询请求   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:35:11 
*
 *//*
@Data
@EqualsAndHashCode(callSuper=false)
public class OMDRequest extends BaseXML implements RequestXML{
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
	*//**
	 * 根据查询类生成对应的回执类
	 * @Title: getResponseByClass 
	 * @Description: TODO() 
	 * @param @param entity
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月28日 上午8:45:18
	 *//*
	@Override
	public BaseXML getResponseByClass(BaseXML entity) {
		OMDRequest request = (OMDRequest)entity;
		String OMDType = request.getOMDRequest_OMDType();
		String RptType = request.getParams_RptType();
		BaseXML xml = null;
		if("Full".equals(RptType)){
			xml = queryFull(OMDType);
		}else {
			xml = queryIncremental(OMDType);
		}
		return xml;
	}
	*//**
	 * 
	 * @Title: getFull 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param OMDType
	 * @param @return    设定文件 
	 * @return BaseXML    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 上午9:25:15
	 *//*
	@Override
	public BaseXML queryFull(String OMDType) {
		Class<? extends BaseXML> clazz = getResponseClass(OMDType);
		try {
			ResponseXML xml = (ResponseXML) clazz.newInstance();
			return xml.createFullEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public BaseXML queryIncremental(String OMDType) {
		Class<? extends BaseXML> clazz = getResponseClass(OMDType);
		try {
			ResponseXML xml = (ResponseXML) clazz.newInstance();
			return xml.createIncrementalEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private Class<? extends BaseXML> getResponseClass(String OMDType) {
		switch (OMDType) {
		case "EBRPSInfo":
			return EBRPSInfo.class;
		case "EBRASInfo":
			return EBRASInfo.class;
		case "EBRDTInfo":
			return EBRDTInfo.class;
		case "EBRPSState":
			return EBRPSState.class;
		case "EBRASState":
			return EBRASState.class;
		case "EBRDTState":
			return EBRDTState.class;
		case "EBMBrdLog":
			return EBMBrdLog.class;
		default:
			return BaseXML.class;
		}
	}
}
*/