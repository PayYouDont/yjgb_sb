package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceLog;

public interface DeviceLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceLog record);

    int insertSelective(DeviceLog record);

    DeviceLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceLog record);

    int updateByPrimaryKey(DeviceLog record);
}