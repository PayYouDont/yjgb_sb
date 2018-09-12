package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import java.io.Serializable;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;

public interface TaskMapper extends BaseDao<Task, Serializable>{
    Task selectByJobName(String jobName);
}