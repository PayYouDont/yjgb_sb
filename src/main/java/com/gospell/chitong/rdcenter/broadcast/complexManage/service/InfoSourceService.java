package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;

public interface InfoSourceService {
	
	List<Infosource> list(Map<String,Object> map);
	
}
