package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;

public interface DevModelParamRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DevModelParamRelation record);

    int insertSelective(DevModelParamRelation record);

    DevModelParamRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DevModelParamRelation record);

    int updateByPrimaryKey(DevModelParamRelation record);
    
    List<DevModelParamRelation>selectByParam(Map<String,Object> map);
}