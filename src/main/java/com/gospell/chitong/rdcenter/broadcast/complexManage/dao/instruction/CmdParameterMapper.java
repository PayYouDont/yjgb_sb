package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDParameter;

public interface CmdParameterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDParameter record);

    int insertSelective(CMDParameter record);

    CMDParameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDParameter record);

    int updateByPrimaryKey(CMDParameter record);
}