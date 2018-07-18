package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    
    Task selectByJobName(String jobName);
}