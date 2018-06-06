package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AdministrativeService;

@Controller
@RequestMapping("/administrativeAction")
public class AdministrativeAction extends BaseAction{
	
	@Resource
	public AdministrativeService service;
	
	@RequestMapping("/getTreeByCode")
	@ResponseBody
	public String getTreeByCode() {
		return service.getTreeStr(getUser().getAreaCode());
	}
	
	//getTreeBySystem
	@RequestMapping("/getTreeBySystem")
	@ResponseBody
	public String getTreeBySystem() {
		return service.getTreeStr(serverProperties.getAreaCode());
	}
}
