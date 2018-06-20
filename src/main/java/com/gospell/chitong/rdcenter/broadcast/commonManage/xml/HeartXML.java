package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.util.XMLUitl;

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
	
	public static void cheartHeartXMLTar(){
		HeartXML xml = new HeartXML();
		xml.setEBDVersion("1");
		xml.setEBDID("01234000000000001010101010000000000000001");
		xml.setEBDType("ConnectionCheck");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		Date now = new Date();
		Date time = new Date(now.getTime()+1500);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		xml.setEBDTime(sdf.format(now));
		xml.setRptTime(sdf.format(time));
		String outPath = "D:\\tar\\heart\\send";
		XMLUitl.createXMLTar(xml.getMap(),outPath, xml.getEBDID());
	}
}
