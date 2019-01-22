package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDFreq;

public interface CmdFreqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDFreq record);

    int insertSelective(CMDFreq record);

    CMDFreq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDFreq record);

    int updateByPrimaryKey(CMDFreq record);
}