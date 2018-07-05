package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EBRPSInfo extends BaseXML implements ResponseXML{
	private String EBRPS_RptTime;
	private String EBRPS_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRPS_EBRID;
	private String EBRPS_EBRName;
	private String EBRPS_Address;
	private String EBRPS_Contact;
	private String EBRPS_PhoneNumber;
	private String EBRPS_Longitude;
	private String EBRPS_Latitude;
	private String EBRPS_URL;
	
	public Map<String,Object> getEBRPSInfoMap(){
		Map<String,Object> EBRPSInfo = new LinkedHashMap<>();
		Map<String,Object> EBRPS = new LinkedHashMap<>();
		EBRPS.put("RptTime", getEBRPS_RptTime());
		EBRPS.put("RptType", getEBRPS_RptType());
		Map<String,Object> RelatedEBRPS = new LinkedHashMap<>();
		RelatedEBRPS.put("EBRID", getRelatedEBRPS_EBRID());
		EBRPS.put("RelatedEBRPS", RelatedEBRPS);
		EBRPS.put("EBRID", getEBRPS_EBRID());
		EBRPS.put("EBRName", getEBRPS_EBRName());
		EBRPS.put("Address", getEBRPS_Address());
		EBRPS.put("Contact", getEBRPS_Contact());
		EBRPS.put("PhoneNumber", getEBRPS_PhoneNumber());
		EBRPS.put("Longitude", getEBRPS_Longitude());
		EBRPS.put("Latitude", getEBRPS_Latitude());
		EBRPS.put("URL", getEBRPS_URL());
		EBRPSInfo.put("EBRPS", EBRPS);
		return EBRPSInfo;
	}
	
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRPSInfo", getEBRPSInfoMap());
		return root;
	}
	/**
	 * 创建平台信息tar包
	 * @Title: createTar 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param prop
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:05:39
	 */
	public static String createTar(ServerProperties prop){
		EBRPSInfo info = createEntity(prop);
		return TarUtil.createXMLTar(info,prop.getTarOutPath(), info.getEBD_EBDID());
	}
	/**
	 * 根据配置文件创建平台信息
	 * @Title: createEntity 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param prop
	 * @param @return    设定文件 
	 * @return EBRPSInfo    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:05:18
	 */
	public static EBRPSInfo createEntity(ServerProperties prop){
		EBRPSInfo info = new EBRPSInfo();
		info.setEBD_EBDVersion("1.0");
		info.setEBD_EBDID("10"+prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd")+EBDcodeUtil.getEBDIDCode());
		info.setEBD_EBDType("EBRDTInfo");
		info.setSRC_EBRID(prop.getSRC_EBRID());
		info.setDEST_EBRID(prop.getDEST_EBRID());
		info.setEBD_EBDTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		info.setEBRPS_RptTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		info.setEBRPS_RptType("Sync");
		info.setRelatedEBRPS_EBRID(prop.getDEST_EBRID());
		info.setEBRPS_EBRID(prop.getSRC_EBRID());
		info.setEBRPS_EBRName(prop.getManageName());
		info.setEBRPS_Address(prop.getUnitName());
		info.setEBRPS_Contact("管理员");
		info.setEBRPS_PhoneNumber("15111111111");
		info.setEBRPS_Longitude(prop.getAreaLongitude());
		info.setEBRPS_Latitude(prop.getAreaLatitude());
		info.setEBRPS_URL(prop.getServer_ip()+prop.getServer_port()+"/nodeAction/upload");
		return info;
	}
	/**
	 * 发送平台信息
	 * @Title: sendEBRPSInfo 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param prop
	 * @param @throws Exception    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:04:58
	 */
	public static void sendEBRPSInfo(ServerProperties prop) throws Exception {
		String tarPath = EBRPSInfo.createTar(prop);
		Map<String,File> tarMap = new LinkedHashMap<>();
		File tar = new File(tarPath);
		tarMap.put(tar.getName(), tar);
		String url = prop.getSendUrl();
		String outPath = prop.getTarInPath()+File.separatorChar+tar.getName();
		HttpClientUtil.sendPostTar(url, tarMap, outPath);
	}
	/**
	 * 获取平台全量信息
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:04:25
	 */
	@Override
	public BaseXML createFullEntity() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return createEntity(prop);
	}
	/**
	 * 获取平台增量信息
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月5日 下午5:04:43
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		EBRPSInfo info = new EBRPSInfo();
		info.setEBD_EBDVersion("1.0");
		info.setEBD_EBDID("10"+prop.getSRC_EBRID()+DateUtils.getDate("yyyyMMdd")+EBDcodeUtil.getEBDIDCode());
		info.setEBD_EBDType("EBRDTInfo");
		info.setSRC_EBRID(prop.getSRC_EBRID());
		info.setDEST_EBRID(prop.getDEST_EBRID());
		info.setEBD_EBDTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		return info;
	}
}
