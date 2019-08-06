package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;

public interface NodeService extends BaseService<Node,Integer>{

	int deleteByIds(Integer[] ids) throws Exception;
    
    String checkNodesToJsonStr(List<Node> nodes);
    
    List<Node> checkNodes(List<Node> nodes);
    
    Map<String,Object> receiveTar(HttpServletRequest request) throws Exception;
    EBD_EBDResponse createResponse(String tarPath);
}
