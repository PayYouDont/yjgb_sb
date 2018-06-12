package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DeviceParamVal;

public interface DeviceParamValMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceParamVal record);

    int insertSelective(DeviceParamVal record);

    DeviceParamVal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceParamVal record);

    int updateByPrimaryKey(DeviceParamVal record);
    
    List<DeviceParamVal> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}