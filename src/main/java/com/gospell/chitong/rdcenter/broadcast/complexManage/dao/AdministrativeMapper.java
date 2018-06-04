package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;

public interface AdministrativeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);
    
    
}