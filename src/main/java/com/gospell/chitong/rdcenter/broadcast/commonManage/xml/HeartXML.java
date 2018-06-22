package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

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
	private String ConnectionCheck_RptTime;
	@Override
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("ConnectionCheck",getConnectionCheck());
		return root;
	}
	public Map<String,Object> getConnectionCheck(){
		Map<String,Object> ConnectionCheck = new LinkedHashMap<>();
		ConnectionCheck.put("RptTime",getConnectionCheck_RptTime());
		return ConnectionCheck;
	}
	
	public static void createHeartXMLTar(String sendPath){
		HeartXML xml = new HeartXML();
		xml.setEBD_EBDVersion("1");
		xml.setEBD_EBDID("01234000000000001010101010000000000000001");
		xml.setEBD_EBDType("ConnectionCheck");
		xml.setSRC_EBRID("23400000000000101010101");
		xml.setDEST_EBRID("33415000000000101010101");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		xml.setEBD_EBDTime(sdf.format(now));
		xml.setConnectionCheck_RptTime(sdf.format(now));
		TarUtil.createXMLTar(xml.getMap(),sendPath, xml.getEBD_EBDID());
	}
}
