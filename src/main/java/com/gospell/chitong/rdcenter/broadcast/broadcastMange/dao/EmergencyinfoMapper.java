package com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao;

import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;

public interface EmergencyinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emergencyinfo record);

    int insertSelective(Emergencyinfo record);

    Emergencyinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emergencyinfo record);

    int updateByPrimaryKeyWithBLOBs(Emergencyinfo record);

    int updateByPrimaryKey(Emergencyinfo record);
    
    List<Emergencyinfo> queryByPage(Page page);
}