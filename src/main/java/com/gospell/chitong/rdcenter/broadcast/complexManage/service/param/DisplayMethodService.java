package com.gospell.chitong.rdcenter.broadcast.complexManage.service.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;

public interface DisplayMethodService {
	
	Displaymethod findById(Integer id);
	
	int save(Displaymethod entity) throws Exception;
	
	int delete(Integer id) throws Exception;
	
	List<Displaymethod> list(Map<String, Object> map);
	
	int count(Map<String,Object> map);
}
