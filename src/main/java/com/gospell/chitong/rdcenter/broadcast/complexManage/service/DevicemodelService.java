package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;

public interface DevicemodelService {
	
	List<Devicemodel> list(Map<String,Object> map);
	
}
