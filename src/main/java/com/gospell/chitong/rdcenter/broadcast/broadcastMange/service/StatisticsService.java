package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
	
	List<Map<String, Object>> getStateData() throws Exception;
	
	List<Map<String,Object>> getStatusData() throws Exception;
	
	Map<String, Object> getTypeData(String option) throws Exception;
}
