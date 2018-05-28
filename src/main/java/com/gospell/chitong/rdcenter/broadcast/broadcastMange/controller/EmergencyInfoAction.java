package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;

@Controller
@RequestMapping("/emergencyInfoAction")
public class EmergencyInfoAction {
	
	@Resource
	private EmergencyInfoService service;
	
	@RequestMapping("/test")
	//@ResponseBody
	public String test() {
		//Emergencyinfo info = service.selectByPrimaryKey(1);
		return "test/test";
	}
	
	@RequestMapping("/test2")
	//@ResponseBody
	public String test2(Model model) {
		Emergencyinfo info = service.selectByPrimaryKey(1);
		model.addAttribute("info",info);
		System.out.println(info);
		return "test/test";
	}
}
