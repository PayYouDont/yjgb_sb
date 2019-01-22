package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDRebackType;

public interface CmdRebackTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDRebackType record);

    int insertSelective(CMDRebackType record);

    CMDRebackType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDRebackType record);

    int updateByPrimaryKey(CMDRebackType record);
}