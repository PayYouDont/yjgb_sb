package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;

public interface DeviceinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deviceinfo record);

    int insertSelective(Deviceinfo record);

    Deviceinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deviceinfo record);

    int updateByPrimaryKey(Deviceinfo record);
}