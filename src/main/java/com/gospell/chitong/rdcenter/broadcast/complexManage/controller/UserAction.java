package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.RoleService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.UserService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(description = "用户操作相关接口")
@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService service;
	
	@Resource
	private RoleService roleService;
	
	@GetMapping("/login")
	public String toLogin() {
		return "login/login";
	}
	
	@GetMapping("/toList")
	public String toList(){
		return "complex/sys/user_list";
	}
	
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id){
		User user = null;
		if(id!=null) {
			user = service.selectById(id);
		}else{
			user = new User();
		}
		List<Role> roleList = roleService.list(new HashMap<>());
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return "complex/sys/user_edit";
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
	@ApiOperation(value="登录", notes="登录接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "name", value = "用户名", required = true ,dataType = "String"),
          @ApiImplicitParam(name = "password", value = "密码", required = true ,dataType = "String"),
    })
	@PostMapping("/login")
	@ResponseBody
	public HashMap<String,Object> login(String password,String name) {
		password = MD5Util.encrypt(name, password);
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return JsonWrapper.successWrapper();
		}catch(AuthenticationException e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper("登陆异常,请稍后再试");
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
	
	@RequestMapping("/save")
	@ResponseBody
	public HashMap<String,Object> updateUser(User user){
		Integer id = user.getId();
		boolean flag = true;
		if(id!=null) {
			User olduser = service.selectById(id);
			String oldPwd = olduser.getPassword();
			//判断密码是否更改了，如果更改了就再加密，没有则不处理
			flag = !oldPwd.equals(user.getPassword());
		}
		if(flag) {//加密密码
			String newPwd = MD5Util.encrypt(user.getName(), user.getPassword());
			user.setPassword(newPwd);
		}
		try {
			service.save(user);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	@PostMapping("/querryUser")
	@ResponseBody
	public HashMap<String,Object> querryUser(Page page){
		Map<String,Object> map = page.getMap();
		List<User> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			service.deleteById(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
}
