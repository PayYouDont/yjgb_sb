package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;
import java.util.LinkedHashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

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
	
	private String EBRAS_RptTime;
	private String EBRAS_EBRID;
	private String EBRAS_StateCode;
	private String EBRAS_StateDesc;
	
	public Map<String,Object> getEBRASMap(){
		Map<String,Object> EBRAS = new LinkedHashMap<String, Object>();
		EBRAS.put("RptTime", getEBRAS_RptTime());
		EBRAS.put("RptTime", getEBRAS_EBRID());
		EBRAS.put("RptTime", getEBRAS_StateCode());
		EBRAS.put("RptTime", getEBRAS_StateDesc());
		return EBRAS;
	}
	
	public Map<String,Object> getEBRASStateMap(){
		Map<String,Object> EBRASState = new LinkedHashMap<String, Object>();
		EBRASState.put("EBRAS", getEBRASMap());
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
	 * @date 2018年7月17日 下午4:31:01
	 */
	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return ResponseXML.super.createIncrementalEntity();
	}
}
