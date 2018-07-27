package com.gospell.chitong.rdcenter.broadcast.complexManage.service.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;

public interface AccidentTypeSevice {
	
	int save(Accidenttype entity) throws Exception;
	
	int delete(Integer id) throws Exception;
	
	Accidenttype findById(Integer id);
	
	List<Accidenttype> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
}
