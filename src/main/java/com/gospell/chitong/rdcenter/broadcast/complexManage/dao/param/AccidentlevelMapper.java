package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;

public interface AccidentlevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accidentlevel record);

    int insertSelective(Accidentlevel record);

    Accidentlevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accidentlevel record);

    int updateByPrimaryKey(Accidentlevel record);
    
    List<Accidentlevel> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}