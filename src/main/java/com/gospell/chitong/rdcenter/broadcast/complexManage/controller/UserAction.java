package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.UserService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService service;
	
	@GetMapping("/login")
	public String toLogin() {
		return "login/login";
	}
	
	@GetMapping("/403")
	public String to403() {
		return "error/403";
	}
	
	/**
	 * @Title: login 
	 * @Description: TODO(登录) 
	 * @param @param user
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午3:04:17
	 */
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
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	/**
	 * @Title: logout 
	 * @Description: TODO(登出) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午3:04:29
	 */
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}
	
	/**
	 * @Title: resetPwd 
	 * @Description: TODO(修改密码) 
	 * @param @param userVO
	 * @param @param user
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午3:04:04
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public HashMap<String,Object> resetPwd(UserVO userVO){
		try {
			service.resetPwd(userVO,getUser());
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public HashMap<String,Object> updateUser(User user){
		
		return JsonWrapper.successWrapper();
	}
}
