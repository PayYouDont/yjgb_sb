package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;

public interface TaskMapper extends BaseDao<Task, Integer>{
    Task selectByJobName(String jobName);
}