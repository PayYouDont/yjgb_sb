package com.gospell.chitong.rdcenter.broadcast.util;

import java.util.Date;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static Date DAY = new Date();
	
	public static String getEBDIDCode() {
		Date now = new Date();
		if(DateUtils.getDistanceOfTwoDate(now, DAY)>=1) {//每天重置EBDID
			DAY = new Date();
			EBDID = 0;
		}
		EBDID++;
		return StringUtil.patch("0", 8, EBDID);
	}
	public static String getConnectionCheckCode() {
		Date now = new Date();
		if(DateUtils.getDistanceOfTwoDate(now, DAY)>=1) {//每天重置EBDID
			DAY = new Date();
			EBDID = 0;
		}
		return StringUtil.patch("0", 16, 0);
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
