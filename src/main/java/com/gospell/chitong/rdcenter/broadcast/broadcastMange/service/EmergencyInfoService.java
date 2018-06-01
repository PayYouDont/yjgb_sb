package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;

public interface EmergencyInfoService {
	int deleteByPrimaryKey(Integer id);

    int insert(Emergencyinfo record);

    int insertSelective(Emergencyinfo record);

    Emergencyinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emergencyinfo record);

    int updateByPrimaryKeyWithBLOBs(Emergencyinfo record);

    int updateByPrimaryKey(Emergencyinfo record);
    
    List<Emergencyinfo> queryByCode(String areaCode);
}
