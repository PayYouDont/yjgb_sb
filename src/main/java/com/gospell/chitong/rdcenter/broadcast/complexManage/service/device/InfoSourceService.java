package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Infosource;

public interface InfoSourceService {
	
	List<Infosource> list(Map<String,Object> map);
	
}
