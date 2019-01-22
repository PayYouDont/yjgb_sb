package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDResourceCode;

public interface CmdResourceCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDResourceCode record);

    int insertSelective(CMDResourceCode record);

    CMDResourceCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDResourceCode record);

    int updateByPrimaryKey(CMDResourceCode record);
}