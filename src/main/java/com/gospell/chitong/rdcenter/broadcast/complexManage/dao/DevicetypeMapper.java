package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicetype;

public interface DevicetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicetype record);

    int insertSelective(Devicetype record);

    Devicetype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicetype record);

    int updateByPrimaryKey(Devicetype record);
}