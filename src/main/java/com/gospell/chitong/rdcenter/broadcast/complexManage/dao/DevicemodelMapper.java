package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;

public interface DevicemodelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicemodel record);

    int insertSelective(Devicemodel record);

    Devicemodel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicemodel record);

    int updateByPrimaryKey(Devicemodel record);
}