package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaymethod;

public interface DisplaymethodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Displaymethod record);

    int insertSelective(Displaymethod record);

    Displaymethod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Displaymethod record);

    int updateByPrimaryKey(Displaymethod record);
    
    List<Displaymethod> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}