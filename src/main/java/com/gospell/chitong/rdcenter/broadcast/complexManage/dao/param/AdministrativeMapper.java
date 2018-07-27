package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;

public interface AdministrativeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Administrative record);

    int insertSelective(Administrative record);

    Administrative selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Administrative record);

    int updateByPrimaryKey(Administrative record);
    
    List<Administrative> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}