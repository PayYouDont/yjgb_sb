package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo.EBRASStateVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 适配器状态
* @ClassName: EBRASState 
* @Description: TODO(  适配器状态   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:33:35 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASState extends BaseXML implements ResponseXML{
	
	private List<EBRASStateVO> EBRASState_EBRAS;
	
	public Map<String,Object> getEBRASStateMap(){
		Map<String,Object> EBRASState = new LinkedHashMap<String, Object>();
		Map<String,Object> EBRAS = new IdentityHashMap<>();
		for(EBRASStateVO vo:getEBRASState_EBRAS()) {
			EBRAS.put(new String("EBRAS"), vo.getMap());
		}
		EBRASState.put(null, EBRAS);
		return EBRASState;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRASState", getEBRASStateMap());
		return root;
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:30:59
	 */
	@Override
	public BaseXML createFullEntity() {
		EBRASState state = (EBRASState)createBaseXML(EBRASState.class);
		DeviceInfoService service = ApplicationContextRegister.getBean(DeviceInfoService.class);
		List<Deviceinfo> deviceinfos = service.getRegistListByType("适配");
		state.setEBRASState_EBRAS(EBRASStateVO.getList(deviceinfos, state));
		return state;
	}
	/** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:31:01
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		return (EBRASState)createBaseXML(EBRASState.class);
	}
}
