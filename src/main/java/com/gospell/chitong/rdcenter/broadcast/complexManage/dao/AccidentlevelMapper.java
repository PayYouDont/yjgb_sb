package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidentlevel;

public interface AccidentlevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accidentlevel record);

    int insertSelective(Accidentlevel record);

    Accidentlevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accidentlevel record);

    int updateByPrimaryKey(Accidentlevel record);
}