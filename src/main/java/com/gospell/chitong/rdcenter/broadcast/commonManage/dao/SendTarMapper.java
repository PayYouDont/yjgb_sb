package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;

public interface SendTarMapper {
    int deleteByPrimaryKey(String ebdid);

    int insert(SendTar record);

    int insertSelective(SendTar record);

    SendTar selectByPrimaryKey(String ebdid);

    int updateByPrimaryKeySelective(SendTar record);

    int updateByPrimaryKey(SendTar record);
}