package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Controller
public class BaseAction {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	protected ServerProperties serverProperties;
	
	public User getUser() {
		return ShiroUtils.getUser();
	}
	
	public Integer getUserId() {
		return getUser().getId();
	}
	
	public String getUserName() {
		return getUser().getName();
	}
	public String getUserAreaCode(){
		return getUser().getAreaCode();
	}
}
