package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeAction extends BaseAction{
	
	@RequestMapping("/")
	public String toHome() {
		return "login/login";
	}
	
	@RequestMapping("loadMap")
	public String loadMap() {
		return "common/map";
	}
}
