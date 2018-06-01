package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Controller
public class BaseAction {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	public User getUser() {
		return ShiroUtils.getUser();
	}
	
	public Integer getUserId() {
		return getUser().getId();
	}
	
	public String getUserName() {
		return getUser().getName();
	}
}
