package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;

public interface InfosourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Infosource record);

    int insertSelective(Infosource record);

    Infosource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Infosource record);

    int updateByPrimaryKey(Infosource record);
    
    List<Infosource> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}