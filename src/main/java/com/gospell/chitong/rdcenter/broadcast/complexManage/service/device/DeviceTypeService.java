package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;

public interface DeviceTypeService {
	int deleteById(Integer id) throws Exception;

	Devicetype selectById(Integer id);

	int save(Devicetype entity) throws Exception;
	
	List<Devicetype> list(Map<String, Object> map);

	int count(Map<String, Object> map);
}
