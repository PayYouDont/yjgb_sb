package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceinfoService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/deviceinfoAction")
public class DeviceinfoAction {
	@Resource
	private DeviceinfoService service;
	
	@RequestMapping("/findByCodes")
	@ResponseBody
	public HashMap<String,Object> findByCodes(String code){
		if(StringUtils.isEmpty(code)) {
			return JsonWrapper.failureWrapper();
		}
		//未完
		return null;
	}
}
