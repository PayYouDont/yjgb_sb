package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicetype;

public interface DevicetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicetype record);

    int insertSelective(Devicetype record);

    Devicetype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicetype record);

    int updateByPrimaryKey(Devicetype record);
    
    List<Devicetype> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}