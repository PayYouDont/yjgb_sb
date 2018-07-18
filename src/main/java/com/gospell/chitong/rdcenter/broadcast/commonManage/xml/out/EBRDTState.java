package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 终端状态
* @ClassName: EBRDTState 
* @Description: TODO(  终端状态   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:02:33 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTState extends BaseXML implements ResponseXML{
	private String EBRDT_RptTime;
	private String EBRDT_EBRID;
	private String EBRDT_StateCode;
	private String EBRDT_StateDesc;
	
	public Map<String,Object> getEBRDTMap(){
		Map<String,Object> EBRDT = new LinkedHashMap<String, Object>();
		EBRDT.put("RptTime", getEBRDT_RptTime());
		EBRDT.put("RptTime", getEBRDT_EBRID());
		EBRDT.put("RptTime", getEBRDT_StateCode());
		EBRDT.put("RptTime", getEBRDT_StateDesc());
		return EBRDT;
	}
	
	public Map<String,Object> getEBRDTStateMap(){
		Map<String,Object> EBRDTState = new LinkedHashMap<String, Object>();
		EBRDTState.put("EBRDT", getEBRDTMap());
		return EBRDTState;
	}
	public Map<String,Object> getMap(){
		Map<String,Object> root = super.getMap();
		root.put("EBRDTState", getEBRDTStateMap());
		return root;
	}
	/** 
	 * <p>Title: createFullEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createFullEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:31:27
	 */
	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return ResponseXML.super.createFullEntity();
	}
	/** 
	 * <p>Title: createIncrementalEntity</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML#createIncrementalEntity() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月17日 下午4:31:29
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return ResponseXML.super.createIncrementalEntity();
	}
}
