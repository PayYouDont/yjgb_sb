package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/nodeAction")
public class NodeAction extends BaseAction{

	@Resource
	private NodeService service;
	@Resource
	private EmergencyInfoService emergencyInfoService;
	@GetMapping("/toList")
	public String toList() {
		return "broadcast/node_list";
	}
	
	@GetMapping("/toNodeNews")
	public String toNodeNews() {
		return "broadcast/nodenNews_list";
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
		Map<String,Object> map = page.getMap();
		List<Node> list = service.list(map);
		int total = service.count(page.getMap());
		System.out.println(JsonWrapper.wrapperPage(list, total));
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
			logger.error("删除节点失败",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	
	@RequestMapping("/checkNode")
	@ResponseBody
	public HashMap<String,Object> checkNode(@RequestBody List<Node> nodes){		
		try {
			List<Node> list = service.checkNodes(nodes);
			//WebScoketServer.sendInfo(jsonStr);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	@RequestMapping("/upload")
	@ResponseBody
	public HashMap<String,Object> upload(HttpServletRequest request,HttpServletResponse response){		
		try {
			String outTarPath = service.receiveTar(request);
			OutputStream out = response.getOutputStream();
			InputStream in = new FileInputStream(new File(outTarPath));
			FileUtil.wirteFile(in, out);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("接收tar包异常:"+e);
			return JsonWrapper.failureWrapper();
		}
	}
	@RequestMapping("/showNodeNews")
	@ResponseBody
	public String showNodeNews(){
		String path = "D:\\tar\\EBDT_10234000000000001010101010000000000002889_in.tar";
		File tarfile = new File(path);
		EBM ebm = service.getEbmFromTar(tarfile);
		try {
			emergencyInfoService.saveXML(ebm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> map = ebm.getEMBMap();
		List<HashMap<String, Object>> list = new LinkedList<>();
		list.add(map);
		return JsonUtil.toJson(JsonWrapper.wrapperPage(list,1));
	}
}