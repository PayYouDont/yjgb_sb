package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;

public interface InfosourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Infosource record);

    int insertSelective(Infosource record);

    Infosource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Infosource record);

    int updateByPrimaryKey(Infosource record);
}