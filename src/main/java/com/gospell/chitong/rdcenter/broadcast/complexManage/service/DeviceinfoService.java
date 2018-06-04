package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;

public interface DeviceinfoService {
	
	int save(Deviceinfo deviceinfo) throws Exception;
	
	int delete(Integer id) throws Exception;
	
	Deviceinfo findById(Integer id);
	
	List<Deviceinfo> queryPage(Map<String,Object> map);
	
	int queryCount(Map<String,Object> map);
	
	List<Deviceinfo> findByCodes(String code);
}
