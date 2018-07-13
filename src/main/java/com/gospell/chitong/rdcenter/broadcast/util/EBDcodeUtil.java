package com.gospell.chitong.rdcenter.broadcast.util;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static String getEBDIDCode() {
		EBDID++;
		return StringUtil.patch("0", 8, EBDID);
	}
	public static String getConnectionCheckCode() {
		EBDID++;
		return StringUtil.patch("0", 16, EBDID);
	}
	public static String getEBDID(Object object) {
		if(object instanceof BaseXML) {
			ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
			if(object instanceof ConnectionCheck) {
				return "01"+prop.getSRC_EBRID()+EBDcodeUtil.getConnectionCheckCode();
			}else {
				return "10"+prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd")+EBDcodeUtil.getEBDIDCode();
			}
		}
		return null;
	}
}
