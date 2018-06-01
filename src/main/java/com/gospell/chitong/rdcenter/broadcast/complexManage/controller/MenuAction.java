package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.MenuService;

@Controller
@RequestMapping("/menuAction")
public class MenuAction extends BaseAction{
	
	@Resource
	private MenuService service;
}
