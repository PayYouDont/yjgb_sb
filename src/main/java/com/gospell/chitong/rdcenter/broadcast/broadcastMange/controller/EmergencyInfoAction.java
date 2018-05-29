package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;

@Controller
@RequestMapping("/emergencyInfoAction")
public class EmergencyInfoAction {
	
	@Resource
	private EmergencyInfoService service;
	
	@Resource
	private ServerProperties server;
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		model.addAttribute("server",server);
		User user = new User();
		user.setName("admin");
		user.setPassword("123456");
		model.addAttribute("user",user);
		return "common/index";
	}
}
