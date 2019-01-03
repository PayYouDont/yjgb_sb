/*package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
*//**
 * 平台状态信息
* @ClassName: EBRPSState 
* @Description: TODO(  平台状态信息   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:26:17 
*
 *//*
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRPSState extends BaseXML implements ResponseXML{
	private String EBRPS_RptTime;
	private String EBRPS_EBRID;
	private String EBRPS_StateCode;
	private String EBRPS_StateDesc;
	
	public Map<String,Object> getEBRPSMap(){
		Map<String,Object> EBRAS = new LinkedHashMap<String, Object>();
		EBRAS.put("RptTime", getEBRPS_RptTime());
		EBRAS.put("EBRID", getEBRPS_EBRID());
		EBRAS.put("StateCode", getEBRPS_StateCode());
		EBRAS.put("StateDesc", getEBRPS_StateDesc());
		return EBRAS;
	}
	public Map<String,Object> getEBRPSStateMap(){
		Map<String,Object> EBRPSState = new LinkedHashMap<>();
		EBRPSState.put("EBRPS", getEBRPSMap());
		return EBRPSState;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRPSState", getEBRPSStateMap());
		return root;
	}
	*//** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:55:17
	 *//*
	@Override
	public BaseXML createFullEntity() {
		EBRPSState state = (EBRPSState)createBaseXML(EBRPSState.class);
		state.setEBRPS_RptTime(DateUtils.getDateTime());
		ServerProperties server = ApplicationContextRegister.getBean(ServerProperties.class);
		state.setEBRPS_EBRID(server.getSRC_EBRID());
		state.setEBRPS_StateCode("1");
		state.setEBRPS_StateDesc(EBDcodeUtil.getEBRPSStateDesc());
		return state;
	}

	*//** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:55:17
	 *//*
	@Override
	public BaseXML createIncrementalEntity() {
		return (EBRPSState)createBaseXML(EBRPSState.class);
	}

	*//** 
	 * <p>Title: getResultCode</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultCode() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:55:17
	 *//*
	@Override
	public String getResultCode() {
		// TODO Auto-generated method stub
		return null;
	}

	*//** 
	 * <p>Title: getResultDesc</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#getResultDesc() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:55:17
	 *//*
	@Override
	public String getResultDesc() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/