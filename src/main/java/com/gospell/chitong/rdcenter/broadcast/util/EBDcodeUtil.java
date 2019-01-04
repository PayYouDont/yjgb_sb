package com.gospell.chitong.rdcenter.broadcast.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static Date DAY = new Date();
	//状态描述
	public static String EBRPSStateDesc = "";
	
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
		return StringUtil.patch("0", 16, 0);
	}
	
	private static String createBaseDateCode() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd");
	}
	
	public static String getEBMID() {
		EBDID++;
		return createBaseDateCode()+StringUtil.patch("0",4, EBDID);
	}
	public static String getEBDID(Object object) {
		if(object instanceof EBD) {
			if(object instanceof EBD_ConnectionCheck) {
				ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
				return "01"+prop.getSRC_EBRID()+getConnectionCheckCode();
			}
			return getBaseEBDID();
			
		}
		return null;
	}
	public static String getBaseEBDID() {
		return "10"+createBaseDateCode()+getEBDIDCode();
	}
	public static String getEBRID(Deviceinfo deviceInfo,String lastCode) {
		String code = deviceInfo.getDevaddresscode();
		AdministrativeMapper dao = ApplicationContextRegister.getBean(AdministrativeMapper.class);
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		List<Administrative> list = dao.list(map);
		Integer level = null;
		if(list.size()>0) {
			level = list.get(0).getCodeLevel();
		}
		return level+code+lastCode;
	}
	
	public static String getEBRPSStateDesc() {
		if("".equals(EBRPSStateDesc)) {
			EBRPSStateDesc = "开机";
		}else {
			EBRPSStateDesc = "运行正常";
		}
		return EBRPSStateDesc;
	}
	
	public static void setEBDHeader(Class<? extends EBD> entity) {
		
	}
}
