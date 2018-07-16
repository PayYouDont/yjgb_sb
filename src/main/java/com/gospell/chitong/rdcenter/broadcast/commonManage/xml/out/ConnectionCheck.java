package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
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
		ConnectionCheck xml = new ConnectionCheck();
		xml.setEBD_EBDVersion("1.0");
		xml.setEBD_EBDID("01"+prop.getSRC_EBRID()+EBDcodeUtil.getConnectionCheckCode());
		xml.setEBD_EBDType("ConnectionCheck");
		xml.setSRC_EBRID(prop.getSRC_EBRID());
		xml.setDEST_EBRID(prop.getDEST_EBRID());
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		xml.setEBD_EBDTime(sdf.format(now));
		xml.setConnectionCheck_RptTime(sdf.format(now));
		return TarUtil.createXMLTar(xml.getMap(),prop.getTarOutPath(), xml.getEBD_EBDID());
	}
}
