package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;

public interface StatisticsService {
	
	List<Map<String, Object>> getStateData() throws Exception;
	
	List<Map<String,Object>> getStatusData() throws Exception;
	
	Map<String, Object> getTypeData(String option) throws Exception;
	
	List<Emergencyinfo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
