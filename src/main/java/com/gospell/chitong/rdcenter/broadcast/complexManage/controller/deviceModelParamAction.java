package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DevicemodelparamService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/deviceModelParamAction")
public class deviceModelParamAction extends BaseAction{
	
	@Resource
	private DevicemodelparamService service;
	
	@GetMapping("/toList")
	public String toList(Model model) {
		return "complex/deviceParam_list";
	}
	
	@GetMapping("/toAdd")
	public String toAdd(Model model) {
		model.addAttribute("deviceModelParam", new Devicemodelparam());
		return "complex/deviceParam_edit";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public HashMap<String,Object> queryList(Page page){
		Map<String,Object> map = page.getMap();
		try {
			List<Devicemodelparam> list = service.list(map);
			int total = service.count(map);
			return JsonWrapper.wrapperPage(list, total);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Devicemodelparam param){
		try {
			param.setCreateBy(getUserName());
			service.save(param);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("dmpId", id);
			List<DevModelParamRelation> list = service.findByMap(map);
			if(list.size()>0) {
				return JsonWrapper.failureWrapper("该参数正在使用");
			}
			service.deletById(id);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	
}
