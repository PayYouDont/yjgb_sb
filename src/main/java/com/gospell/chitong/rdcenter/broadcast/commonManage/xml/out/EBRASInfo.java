/*package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo.EBRASInfoVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
*//**
 * 适配器信息
* @ClassName: EBRASInfo 
* @Description: TODO(  适配器信息   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:31:55 
*
 *//*
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASInfo extends BaseXML implements ResponseXML{
	
	 * EBD/EBRASInfo/Params
	 
	private String Params_RptStartTime;
	private String Params_RptEndTime;
	private String Params_RptType;
	private List<EBRASInfoVO> EBRASInfo_EBRAS;
	
	public Map<String,Object> getParamsMap(){
		Map<String,Object> Params = new LinkedHashMap<>();
		Params.put("RptStartTime", getParams_RptStartTime());
		Params.put("RptEndTime", getParams_RptEndTime());
		Params.put("RptType", getParams_RptType());
		return Params;
	}

	public Map<String,Object> getEBRASInfoMap(){
		Map<String,Object> EBRASInfo = new LinkedHashMap<>();
		EBRASInfo.put("Params", getParamsMap());
		Map<String,Object> EBRAS = new IdentityHashMap<>();
		if(getEBRASInfo_EBRAS()!=null) {
			for(EBRASInfoVO ebras:getEBRASInfo_EBRAS()) {
				EBRAS.put(new String("EBRAS"), ebras.getMap());
			}
		}
		//设置一个空节点，解析方法会自动将空节点添加到父节点下，解决节点顺序问题
		EBRASInfo.put(null, EBRAS);
		return EBRASInfo;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRASInfo", getEBRASInfoMap());
		return root;
	}
	*//** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:26
	 *//*
	@Override
	public BaseXML createFullEntity() {
		DeviceInfoService service = ApplicationContextRegister.getBean(DeviceInfoService.class);
		List<Deviceinfo> deviceinfos = service.getRegistListByType("适配");
		EBRASInfo info = (EBRASInfo)createBaseXML(EBRASInfo.class);
		info.setParams_RptStartTime(DateUtils.getDateTime());
		info.setParams_RptEndTime(DateUtils.getDateTime());
		info.setParams_RptType("Full");
		info.setEBRASInfo_EBRAS(EBRASInfoVO.getList(deviceinfos, info));
		return info;
	}
	*//** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 上午11:54:26
	 *//*
	@Override
	public BaseXML createIncrementalEntity() {
		EBRASInfo info = (EBRASInfo)createBaseXML(EBRASInfo.class);
		info.setParams_RptStartTime(DateUtils.getDateTime());
		info.setParams_RptEndTime(DateUtils.getDateTime());
		info.setParams_RptType("Incremental");
		return info;
	}
}
*/