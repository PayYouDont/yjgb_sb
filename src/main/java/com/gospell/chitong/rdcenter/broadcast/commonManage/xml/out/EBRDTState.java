/*package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo.EBRDTStateVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;

import lombok.Data;
import lombok.EqualsAndHashCode;
*//**
 * 终端状态
* @ClassName: EBRDTState 
* @Description: TODO(  终端状态   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:02:33 
*
 *//*
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTState extends BaseXML implements ResponseXML{

	private List<EBRDTStateVO> EBRDTState_EBRDT;
	
	public Map<String,Object> getEBRDTStateMap(){
		Map<String,Object> EBRDTState = new LinkedHashMap<String, Object>();
		Map<String,Object> EBRDT = new IdentityHashMap<>();
		for(EBRDTStateVO vo:getEBRDTState_EBRDT()) {
			EBRDT.put(new String("EBRDT"), vo.getMap());
		}
		EBRDTState.put(null, EBRDT);
		return EBRDTState;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRDTState", getEBRDTStateMap());
		return root;
	}
	*//** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:31:27
	 *//*
	@Override
	public BaseXML createFullEntity() {
		EBRDTState state = (EBRDTState)createBaseXML(EBRDTState.class);
		DeviceInfoService service = ApplicationContextRegister.getBean(DeviceInfoService.class);
		List<Deviceinfo> deviceinfos = service.getRegistListByType("终端");
		state.setEBRDTState_EBRDT(EBRDTStateVO.getList(deviceinfos, state));
		return state;
	}
	*//** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:31:29
	 *//*
	@Override
	public BaseXML createIncrementalEntity() {
		return (EBRDTState)createBaseXML(EBRDTState.class);
	}
}
*/