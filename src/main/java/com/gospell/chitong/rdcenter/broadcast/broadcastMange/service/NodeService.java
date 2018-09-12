package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;

public interface NodeService extends BaseService<Node>{

	int deleteByIds(Integer[] ids) throws Exception;
    
    String checkNodesToJsonStr(List<Node> nodes);
    
    List<Node> checkNodes(List<Node> nodes);
    
    String receiveTar(HttpServletRequest request) throws Exception;
    
    EBM getEbmFromTar(File tarfile);
}
