package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBMStateRequest;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.OMDRequest;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;

@Data
public class BaseXML {
	// 命名规则为"上级节点名_下级节点名"格式，如下：
	protected String EBD_EBDVersion;
	/*
	 * 41位数字码，格式为：类型码（2位数字码）+数据包来源对象ID（23位数字码）+顺序码（16位数字码）。 数据包来源对象ID见GY/T
	 * XXX—XXXX《应急广播资源分类及编码规范》 心跳检测的类型码为01，16位顺序码始终为0。 其他数据包的类型码为10，顺序码从0开始递增。
	 * 
	 */
	protected String EBD_EBDID;
	protected String EBD_EBDType;
	protected String SRC_EBRID;
	protected String SRC_URL;
	protected String DEST_EBRID;
	protected String EBD_EBDTime;

	public static final Logger logger = LoggerFactory.getLogger(BaseXML.class);
	
	public Map<String, Object> getMap() {
		Map<String, Object> root = new LinkedHashMap<>();
		root.put("EBDVersion", getEBD_EBDVersion());
		root.put("EBDID", getEBD_EBDID());
		root.put("EBDType", getEBD_EBDType());
		root.put("SRC", getSRC());
		root.put("DEST", getDEST());
		root.put("EBDTime", getEBD_EBDTime());
		return root;
	};

	public Map<String, Object> getSRC() {
		Map<String, Object> src = new LinkedHashMap<>();
		src.put("EBRID", getSRC_EBRID());
		if (getSRC_URL() != null) {
			src.put("URL", getSRC_URL());
		}
		return src;
	}

	public Map<String, Object> getDEST() {
		Map<String, Object> dest = new LinkedHashMap<>();
		dest.put("EBRID", getDEST_EBRID());
		return dest;
	}

	public static Class<? extends BaseXML> getClassByEBDType(String EBDType) {
		switch (EBDType) {
		case "OMDRequest":
			return OMDRequest.class;
		case "EBM":
			return EBM.class;
		case "EBMStateRequest":
			return EBMStateRequest.class;
		case "ConnectionCheck":
			return ConnectionCheck.class;
		case "EBDResponse":
			return EBDResponse.class;
		default:
			return BaseXML.class;
		}
	}

	public BaseXML getResponseByClass(BaseXML entity) {
		EBDResponse response = new EBDResponse();
		response.setEBD_EBDVersion(entity.getEBD_EBDVersion());
		response.setEBD_EBDID(entity.getEBD_EBDID());
		response.setEBD_EBDType("EBDResponse");
		response.setSRC_EBRID(entity.getDEST_EBRID());
		response.setDEST_EBRID(entity.getSRC_EBRID());
		response.setEBD_EBDTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		return response;
	}
	public static BaseXML createBaseXML(Class<? extends BaseXML> clazz){
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		try {
			BaseXML xml = clazz.newInstance();
			xml.setEBD_EBDVersion(prop.getServer_version());
			xml.setEBD_EBDID(EBDcodeUtil.getEBDID(xml));
			xml.setEBD_EBDType(clazz.getSimpleName());
			xml.setSRC_EBRID(prop.getSRC_EBRID());
			xml.setDEST_EBRID(prop.getDEST_EBRID());
			xml.setEBD_EBDTime(DateUtils.getDateTime());
			return xml;
		}catch(Exception e) {
			logger.error("创建xml错误",e);
		}
		return null;
	}
}
