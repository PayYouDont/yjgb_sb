package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/nodeAction")
public class NodeAction extends BaseAction{

	@Resource
	private NodeService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "broadcast/node_list";
	}
	
	@GetMapping("/toAdd")
	public String toAdd(Model model,Integer id) {
		Node node = null;
		if(id!=null) {
			node = service.selectById(id);
		}else {
			node = new Node();
		}
		model.addAttribute("node", node);
		return "broadcast/node_edit";
	}
	
	@PostMapping("/queryNode")
	@ResponseBody
	public HashMap<String,Object> queryNode(Page page){
		List<Node> list = service.list(page.getMap());
		int total = service.count(page.getMap());
		return JsonWrapper.wrapperPage(list, total);
	}
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Node node){
		try {
			service.save(node);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("保存节点失败",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer[] ids){
		try {
			service.deleteByIds(ids);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("保存节点失败",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
