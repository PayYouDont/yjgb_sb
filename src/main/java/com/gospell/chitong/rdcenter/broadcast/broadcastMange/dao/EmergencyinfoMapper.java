package com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;

public interface EmergencyinfoMapper extends BaseDao<Emergencyinfo, Serializable>{
    int deleteByPrimaryKey(Integer id);

    int insert(Emergencyinfo record);

    int insertSelective(Emergencyinfo record);

    Emergencyinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emergencyinfo record);

    int updateByPrimaryKeyWithBLOBs(Emergencyinfo record);

    int updateByPrimaryKey(Emergencyinfo record);
    
    List<Emergencyinfo> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
 
    List<Emergencyinfo> getByStartTime(Map<String,Object> map);
    
    Emergencyinfo getByEmb_id(String ebmId);
    
    int updateByEmb_idSelective(Emergencyinfo record);
    
    List<Emergencyinfo> selectByDate(Map<String,Object> map);
}