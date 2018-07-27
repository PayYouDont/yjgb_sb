package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;

public interface DeviceModelParamService {

	Devicemodelparam findById(Integer id);
	
	List<Devicemodelparam> list(Map<String,Object>map);
	
	int count(Map<String,Object>map);
	
	int save(Devicemodelparam device) throws Exception;
	
	int deletById(Integer id) throws Exception;
	
	List<DevModelParamRelation> findByMap(Map<String,Object> map);
}
