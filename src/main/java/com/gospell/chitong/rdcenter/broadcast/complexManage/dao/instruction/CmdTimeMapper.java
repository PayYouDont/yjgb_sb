package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDTime;

public interface CmdTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDTime record);

    int insertSelective(CMDTime record);

    CMDTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDTime record);

    int updateByPrimaryKey(CMDTime record);
}