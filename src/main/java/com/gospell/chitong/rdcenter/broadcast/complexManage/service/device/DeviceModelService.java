package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;

public interface DeviceModelService {
	
	Devicemodel selectById(Integer id);
	
	int save(Devicemodel entity) throws Exception;
	
	int delete(Integer id) throws Exception;
	
	List<Devicemodel> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	List<Devicemodelparam> deviceModelParamList(Map<String, Object> map);
	
	List<Devicetype> deviceTypeList(Map<String, Object> map);
	
	List<DevModelParamRelation> devModelParamRelationList(Map<String, Object> map);
	
	int saveDMPR(Integer modelId,String paramIds) throws Exception;
	
	int deleteDMPRByDevModelId(Integer devModelId) throws Exception;
	
	List<Devicemodelparam> getDevParmByDevicemodel(Devicemodel deviceModel);
}
