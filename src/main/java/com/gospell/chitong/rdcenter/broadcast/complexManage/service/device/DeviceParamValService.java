package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceParamVal;

public interface DeviceParamValService {
	
	DeviceParamVal findById(Integer id);
	
	int delete(Integer id);
	
	int save(DeviceParamVal deviceParamVal);
	
	List<DeviceParamVal> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
