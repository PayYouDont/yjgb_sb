package com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;

public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);
    
    List<Node> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}