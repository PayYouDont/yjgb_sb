package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.UserLog;

public interface UserLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    UserLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKeyWithBLOBs(UserLog record);

    int updateByPrimaryKey(UserLog record);
}