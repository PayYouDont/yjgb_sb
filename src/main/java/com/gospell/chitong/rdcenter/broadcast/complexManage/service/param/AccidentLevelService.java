package com.gospell.chitong.rdcenter.broadcast.complexManage.service.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;

public interface AccidentLevelService {

	Accidentlevel findById(Integer id);
	
	int save(Accidentlevel entity) throws Exception;

	int delete(Integer id) throws Exception;
	
	List<Accidentlevel> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
}
