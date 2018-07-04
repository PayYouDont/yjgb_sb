package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;

public interface NodeService {
	
	int deleteById(Integer id) throws Exception;

	int deleteByIds(Integer[] ids) throws Exception;
	
    Node selectById(Integer id);
    
    int save(Node node) throws Exception;
    
    List<Node> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
    
    String checkNodesToJsonStr(List<Node> nodes);
    
    List<Node> checkNodes(List<Node> nodes);
    
    String receiveTar(HttpServletRequest request) throws Exception;
    EBM getEbmFromTar(File tarfile);
}
