package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;

public interface AdministrativeService {
	Administrative findByCode(String code);
	
	List<Administrative> list(Map<String,Object> map);
	
	String getTreeStr(String areaCode);
	
	int count(Map<String,Object> map);
}
