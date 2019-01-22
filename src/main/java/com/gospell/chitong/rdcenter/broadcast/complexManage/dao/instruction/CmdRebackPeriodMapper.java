package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDRebackPeriod;

public interface CmdRebackPeriodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDRebackPeriod record);

    int insertSelective(CMDRebackPeriod record);

    CMDRebackPeriod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDRebackPeriod record);

    int updateByPrimaryKey(CMDRebackPeriod record);
}