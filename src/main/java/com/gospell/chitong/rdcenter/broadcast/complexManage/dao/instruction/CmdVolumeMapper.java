package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMDVolume;

public interface CmdVolumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMDVolume record);

    int insertSelective(CMDVolume record);

    CMDVolume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CMDVolume record);

    int updateByPrimaryKey(CMDVolume record);
}