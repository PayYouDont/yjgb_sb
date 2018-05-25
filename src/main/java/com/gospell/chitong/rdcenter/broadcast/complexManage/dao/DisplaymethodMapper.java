package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaymethod;

public interface DisplaymethodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Displaymethod record);

    int insertSelective(Displaymethod record);

    Displaymethod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Displaymethod record);

    int updateByPrimaryKey(Displaymethod record);
}