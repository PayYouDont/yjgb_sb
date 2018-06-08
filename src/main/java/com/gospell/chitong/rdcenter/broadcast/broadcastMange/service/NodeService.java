package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;

public interface NodeService {
	
	int deleteById(Integer id) throws Exception;

	int deleteByIds(Integer[] ids) throws Exception;
	
    Node selectById(Integer id);
    
    int save(Node node) throws Exception;
    
    List<Node> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}
