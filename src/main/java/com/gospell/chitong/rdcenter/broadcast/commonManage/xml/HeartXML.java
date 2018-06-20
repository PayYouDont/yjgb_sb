package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 心跳检测XML
* @ClassName: HeartXML 
* @Description: TODO(用于生成心跳检测XML) 
* @author peiyongdong
* @date 2018年6月15日 下午4:28:11 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class HeartXML extends BaseXML{
	private String RptTime;
	
	@Override
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("ConnectionCheck",getConnectionCheck());
		return root;
	}
	public Map<String,Object> getConnectionCheck(){
		Map<String,Object> ConnectionCheck = new LinkedHashMap<>();
		ConnectionCheck.put("RptTime",getRptTime());
		return ConnectionCheck;
	}
	/**
	 * exp:
	 *  HeartXML xml = new HeartXML();
		xml.setEBDVersion("1");
		xml.setEBDID("01234000000000001010101010000000000000001");
		xml.setEBDType("ConnectionCheck");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		xml.setEBDTime("2017-06-07 13:40:36");
		xml.setRptTime("YYYY-MM-DD HH:MI:SS");
		String outPath = "C:\\Users\\pay\\Desktop\\xml";
		createXML(xml.getMap(),outPath,"心跳检测222");
	 */
}
