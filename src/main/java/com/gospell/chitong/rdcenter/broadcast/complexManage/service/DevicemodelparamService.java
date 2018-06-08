package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;

public interface DevicemodelparamService {

	List<Devicemodelparam> list(Map<String,Object>map);
	
	int count(Map<String,Object>map);
	
	int save(Devicemodelparam device) throws Exception;
	
	int deletById(Integer id) throws Exception;
	
	List<DevModelParamRelation> findByMap(Map<String,Object> map);
}
