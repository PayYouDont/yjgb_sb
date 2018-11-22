package com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;

public interface EmergencyinfoMapper extends BaseDao<Emergencyinfo, Integer>{
 
    List<Emergencyinfo> getByStartTime(Map<String,Object> map);
    
    Emergencyinfo getByEmb_id(String ebmId);
    
    int updateByEmb_idSelective(Emergencyinfo record);
    
    List<Emergencyinfo> selectByDate(Map<String,Object> map);
}