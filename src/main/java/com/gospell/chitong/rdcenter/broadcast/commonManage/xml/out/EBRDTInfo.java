/*package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo.EBRDTInfoVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;

import lombok.Data;
import lombok.EqualsAndHashCode;
*//**
 * 终端信息
* @ClassName: EBRDTInfo 
* @Description: TODO(  终端信息  ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:00:40 
*
 *//*
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTInfo extends BaseXML implements ResponseXML{
	private List<EBRDTInfoVO> EBRDTInfo_EBRDT;
	
	public Map<String,Object> getEBRDTInfoMap(){
		Map<String,Object> EBRDTInfoMap = new IdentityHashMap<>();
		if(getEBRDTInfo_EBRDT()!=null) {
			for(EBRDTInfoVO ebrdt:getEBRDTInfo_EBRDT()) {
				EBRDTInfoMap.put(new String("EBRDT"), ebrdt.getMap());
			}
		}
		return EBRDTInfoMap;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRDTInfo", getEBRDTInfoMap());
		return root;
	}
	*//** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:29:49
	 *//*
	@Override
	public BaseXML createFullEntity() {
		DeviceInfoService service = ApplicationContextRegister.getBean(DeviceInfoService.class);
		List<Deviceinfo> deviceinfos = service.getRegistListByType("终端");
		EBRDTInfo info = (EBRDTInfo)createBaseXML(EBRDTInfo.class);
		info.setEBRDTInfo_EBRDT(EBRDTInfoVO.getList(deviceinfos, info));
		return info;
	}
	*//** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:29:56
	 *//*
	@Override
	public BaseXML createIncrementalEntity() {
		return (EBRDTInfo)createBaseXML(EBRDTInfo.class);
	}
}
*/