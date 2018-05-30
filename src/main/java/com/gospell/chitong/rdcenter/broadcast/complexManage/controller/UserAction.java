package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.UserService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Controller
@RequestMapping("/userAction")
public class UserAction {
	
	@Resource
	private UserService service;
	
	@Resource
	private ServerProperties properties;
	
	@GetMapping("/login")
	public String toLogin() {
		return "login/login";
	}
	
	@GetMapping("/index")  
    public String page(Model model){  
		model.addAttribute("server",properties);
		User user = ShiroUtils.getUser();
		model.addAttribute("user",user);
        return "common/index";  
    }  
	
	@PostMapping("/login")
	@ResponseBody
	public HashMap<String,Object> login(User user) {
		String password = user.getPassword();
		String username = user.getName();
		password = MD5Util.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return JsonWrapper.successWrapper();
		}catch(AuthenticationException e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
