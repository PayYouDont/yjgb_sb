package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;

public interface DevicemodelparamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicemodelparam record);

    int insertSelective(Devicemodelparam record);

    Devicemodelparam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicemodelparam record);

    int updateByPrimaryKey(Devicemodelparam record);
    
    List<Devicemodelparam> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}