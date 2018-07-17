package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;

public interface ReceiveTarMapper {
    int deleteByPrimaryKey(String id);

    int insert(ReceiveTar record);

    int insertSelective(ReceiveTar record);

    ReceiveTar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ReceiveTar record);

    int updateByPrimaryKey(ReceiveTar record);
}