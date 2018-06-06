package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.MenuService;

@Controller
@RequestMapping("/menuAction")
public class MenuAction extends BaseAction{
	
	@Resource
	private MenuService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("server",serverProperties);
		model.addAttribute("user",getUser());
		model.addAttribute("nvaMenuType","综合网络管理系统");
        return "common/index";
	}
	
}
