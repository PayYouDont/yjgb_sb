package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;

public interface DevicemodelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicemodel record);

    int insertSelective(Devicemodel record);

    Devicemodel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicemodel record);

    int updateByPrimaryKey(Devicemodel record);
    
    List<Devicemodel> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}