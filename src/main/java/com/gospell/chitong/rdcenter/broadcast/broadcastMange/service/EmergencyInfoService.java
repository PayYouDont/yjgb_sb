package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;

public interface EmergencyInfoService {
	int deleteById(Integer id) throws Exception;

    int save(Emergencyinfo record) throws Exception;

    Emergencyinfo selectById(Integer id);
    
    List<Emergencyinfo> queryPage(Map<String,Object> map);
    
    int countPage(Map<String,Object> map);
}
