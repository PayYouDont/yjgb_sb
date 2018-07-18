package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 心跳检测XML
* @ClassName: ConnectionCheck 
* @Description: TODO(用于生成心跳检测XML) 
* @author peiyongdong
* @date 2018年6月15日 下午4:28:11 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ConnectionCheck extends BaseXML{
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
	
	public static String createTar(ServerProperties prop){
		ConnectionCheck xml = (ConnectionCheck)createBaseXML(ConnectionCheck.class);
		xml.setConnectionCheck_RptTime(xml.getEBD_EBDTime());
		String tarName = xml.getEBD_EBDID();
		return TarUtil.createXMLTar(xml,prop.getTarOutPath(),tarName);
	}
}
