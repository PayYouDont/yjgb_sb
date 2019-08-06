package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Node;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.ReceiveTarService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Api(tags = "节点管理")
@Controller
@RequestMapping("/nodeAction")
public class NodeAction extends BaseAction{

	@Resource
	private NodeService service;
	@Resource
	private ReceiveTarService receiveTarService;
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
	
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page){
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
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	@ApiOperation(value="上传文件", notes="上传文件接口")
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response){
		OutputStream out = null;
		InputStream in = null;
		EBD_EBDResponse replyResponse;
		response.setContentType("application/force-download");// 设置强制下载不打开            
		try {
			Map<String,Object> map = service.receiveTar(request);
			out = response.getOutputStream();
			boolean isTar = (boolean)map.get("isTar");
			if(!isTar) {
				replyResponse = new EBD_EBDResponse(EBD_EBDResponse.OTHER_ERROR,"该文件不是tar格式文件");
				String replyOutTarPath = TarUtil.createXMLTarByBean(replyResponse,serverProperties.getReplyOutTarPath(),replyResponse.getEBD().getEBDID());
				File file = new File(replyOutTarPath);
				in = new FileInputStream(file);
				response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
				FileUtil.wirteFile(in, out);
				return;
			}
			boolean isSign = (boolean)map.get("isSign");
			if(!isSign) {
				replyResponse = new EBD_EBDResponse(EBD_EBDResponse.SIGN_VERIF_FAILED,"签名文件验证未通过");
				String replyOutTarPath = TarUtil.createXMLTarByBean(replyResponse,serverProperties.getReplyOutTarPath(),replyResponse.getEBD().getEBDID());
				File file = new File(replyOutTarPath);
				in = new FileInputStream(file);
				response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
				FileUtil.wirteFile(in, out);
				return;
			}
			//验证通过后回复通用
			replyResponse = new EBD_EBDResponse();
			String replyOutTarPath = TarUtil.createXMLTarByBean(replyResponse,serverProperties.getReplyOutTarPath(),replyResponse.getEBD().getEBDID());
			File file = new File(replyOutTarPath);
			in = new FileInputStream(file);
			response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
			FileUtil.wirteFile(in, out,false);
			EBD ebd = (EBD)map.get("ebd");
			// 将tar包信息保存至数据库
			receiveTarService.saveReceiveTar(ebd);
			EBD responseEBD = ebd.creatResponse();
			if(responseEBD != null) {
                TarUtil.sendEBDToSuperior (responseEBD);
			}
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
			replyResponse = new EBD_EBDResponse(EBD_EBDResponse.OTHER_ERROR,e.getMessage());
			String replyOutTarPath = TarUtil.createXMLTarByBean(replyResponse,serverProperties.getReplyOutTarPath(),replyResponse.getEBD().getEBDID());
			try {
				out = response.getOutputStream();
				in = new FileInputStream(new File(replyOutTarPath));
				FileUtil.wirteFile(in, out);
			}catch (Exception e1){
				logger.info(e1.getMessage(),e1);
			}
		}finally {
			FileUtil.closeIO(in,out);
		}
	}
}