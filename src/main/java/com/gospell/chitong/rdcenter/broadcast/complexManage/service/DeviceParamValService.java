package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DeviceParamVal;

public interface DeviceParamValService {
	
	DeviceParamVal findById(Integer id);
	
	int delete(Integer id);
	
	int save(DeviceParamVal deviceParamVal);
	
	List<DeviceParamVal> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
