package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.DateJsonValueProcessor;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.NodeMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Service
public class INodeService implements NodeService {

	@Resource
	private NodeMapper dao;

	@Override
	public int deleteById(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Node selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int save(Node node) throws Exception {
		int i = 0;
		if (node.getId() != null) {
			node.setUpdateBy(ShiroUtils.getUser().getName());
			i = dao.updateByPrimaryKeySelective(node);
		} else {
			node.setCreateBy(ShiroUtils.getUser().getName());
			i = dao.insertSelective(node);
		}
		return i;
	}

	@Override
	public List<Node> list(Map<String, Object> map) {
		List<Node> list = dao.list(map);
		return list;
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public int deleteByIds(Integer[] ids) throws Exception {
		int count = 0;
		for (int i = 0; i < ids.length; i++) {
			count += deleteById(ids[i]);
		}
		return count;
	}

	@Override
	public String checkNodesToJsonStr(List<Node> nodes) {
		for (Node node : nodes) {
			int status = -1;
			if (node.getNodeStatus() == 0) {
				continue;
			}
			try {
				status = HttpClientUtil.checkNode(node);
			} catch (ClientProtocolException e) {
				status = -1;
			} catch (IOException e) {
				status = -2;
			}
			node.setLinkStatus(status);
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		return JSONArray.fromObject(nodes,jsonConfig).toString();
	}

}
