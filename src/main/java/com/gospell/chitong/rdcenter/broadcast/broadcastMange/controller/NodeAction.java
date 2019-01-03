package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.EBD;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "节点管理")
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
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="保存节点", notes="保存节点")
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
	@ApiOperation(value="删除节点", notes="删除节点接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String")
	})
	@Log("删除节点")
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
	@ApiOperation(value="检查节点", notes="检查节点状态接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "nodes", value = "待检测节点集合", required = true ,dataType = "String")
	})
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
	@ApiOperation(value="上传文件", notes="上传文件接口")
	@RequestMapping("/upload")
	@ResponseBody
	public void upload(HttpServletRequest request,HttpServletResponse response){		
		try {
			Map<String,Object> map = service.receiveTar(request);
			OutputStream out = response.getOutputStream();
			boolean isTar = (boolean)map.get("isTar");
			if(!isTar) {
				FileUtil.writeString("该文件不是tar格式文件",out);
				return;
			}
			boolean isSign = (boolean)map.get("isSign");
			if(!isSign) {
				FileUtil.writeString("签名文件验证未通过",out);
				return;
			}
			String tarPath = map.get("tarPath").toString();
			InputStream in = new FileInputStream(new File(tarPath));
			FileUtil.wirteFile(in, out);
			FileUtil.delete(tarPath);
			EBD ebd = (EBD)map.get("ebd");
			EBD responseEBD = ebd.creatResponse();
			if(responseEBD == null) {
				return;
			}
			String tarName = responseEBD.getEBD().getEBDID();
			tarPath = TarUtil.createXMLTarByBean(responseEBD,serverProperties.getTarOutPath(),tarName);
			String result = HttpClientUtil.sendPostFile(serverProperties.getSuperiorUrl(), tarPath);
			// 保存发送tar包信息
			TarUtil.saveSendTar(responseEBD);
			TarUtil.checkEBDResponse(result);
		} catch (Exception e) {
			logger.error("接收tar包异常:"+e);
		}
	}
}