package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 终端信息
* @ClassName: EBRDTInfo 
* @Description: TODO(  终端信息  ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:00:40 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTInfo extends BaseXML implements ResponseXML{
	//EBRDTInfo/EBRDT
	private String EBRDT_RptTime;
	private String EBRDT_RptType;
	private String RelatedEBRPS_EBRID;
	private String EBRDT_EBRID;
	private String EBRDT_EBRName;
	private String EBRDT_Longitude;
	private String EBRDT_Latitude;
	
	public Map<String,Object> getEBRDTInfoMap(){
		Map<String,Object> EBRDTInfoMap = new LinkedHashMap<>();
		Map<String,Object> EBRDT = new LinkedHashMap<>();
		EBRDT.put("RptTime", getEBRDT_RptTime());
		EBRDT.put("EBRDT_RptType", getEBRDT_RptType());
		EBRDT.put("RelatedEBRPS_EBRID", getRelatedEBRPS_EBRID());
		EBRDT.put("EBRDT_EBRID", getEBRDT_EBRID());
		EBRDT.put("EBRDT_EBRName", getEBRDT_EBRName());
		EBRDT.put("EBRDT_Longitude", getEBRDT_Longitude());
		EBRDT.put("EBRDT_Latitude", getEBRDT_Latitude());
		EBRDTInfoMap.put("EBRDT", EBRDT);
		return EBRDTInfoMap;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRDTInfo", getEBRDTInfoMap());
		return root;
	}
	public static EBRDTInfo createEntity(Deviceinfo deviceInfo) {
		EBRDTInfo info = (EBRDTInfo)BaseXML.createBaseXML(EBRDTInfo.class);
		info.setEBRDT_RptTime(DateUtils.getDate("yyyy-MM-dd hh:mm:ss"));
		info.setEBRDT_RptType("Sync");
		info.setRelatedEBRPS_EBRID(info.getSRC_EBRID());
		String code = deviceInfo.getDevaddresscode();
		AdministrativeMapper dao = ApplicationContextRegister.getBean(AdministrativeMapper.class);
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		List<Administrative> list = dao.list(map);
		Integer level = null;
		if(list.size()>0) {
			level = list.get(0).getCodeLevel();
		}
		info.setEBRDT_EBRID(level+code+"10314040401");
		info.setEBRDT_EBRName(deviceInfo.getDevname());
		info.setEBRDT_Latitude(deviceInfo.getLat());
		info.setEBRDT_Longitude(deviceInfo.getLng());
		return info;
	}
	public static String createTar(Deviceinfo deviceInfo) {
		EBRDTInfo info = createEntity(deviceInfo);
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		return TarUtil.createXMLTar(info,prop.getTarOutPath(), info.getEBD_EBDID());
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:56
	 */
	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:56
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: getResultCode</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultCode() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:56
	 */
	@Override
	public String getResultCode() {
		// TODO Auto-generated method stub
		return null;
	}
	/** 
	 * <p>Title: getResultDesc</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultDesc() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:56
	 */
	@Override
	public String getResultDesc() {
		// TODO Auto-generated method stub
		return null;
	}
}
