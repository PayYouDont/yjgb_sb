package com.gospell.chitong.rdcenter.broadcast.complexManage.service.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;

public interface AdministrativeService {
	Administrative findByCode(String code);
	
	List<Administrative> list(Map<String,Object> map);

    List<Administrative> listByCodeLevel(Map<String,Object> map);

	String getTreeStr(String areaCode);
	
	int count(Map<String,Object> map);
}
