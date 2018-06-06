package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaylanguage;

public interface DisplaylanguageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Displaylanguage record);

    int insertSelective(Displaylanguage record);

    Displaylanguage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Displaylanguage record);

    int updateByPrimaryKey(Displaylanguage record);
    
    List<Displaylanguage> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}