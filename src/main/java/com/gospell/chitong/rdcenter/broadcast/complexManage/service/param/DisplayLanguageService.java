package com.gospell.chitong.rdcenter.broadcast.complexManage.service.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;

public interface DisplayLanguageService {
	
	Displaylanguage findById(Integer id);
	
	int save(Displaylanguage entity) throws Exception;
	
	int delete(Integer id) throws Exception;
	
	List<Displaylanguage> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
