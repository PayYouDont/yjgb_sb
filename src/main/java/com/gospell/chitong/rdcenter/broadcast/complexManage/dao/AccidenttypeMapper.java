package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidenttype;

public interface AccidenttypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accidenttype record);

    int insertSelective(Accidenttype record);

    Accidenttype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accidenttype record);

    int updateByPrimaryKey(Accidenttype record);
}