package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(tags = "用户管理")
@RestController
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService service;
	
	@Resource
	private RoleService roleService;
	
	@GetMapping("/login")
	public ModelAndView toLogin() {
		return new ModelAndView("login/login");
	}
	
	@GetMapping("/toList")
	public ModelAndView toList(){
		return new ModelAndView("complex/sys/user_list");
	}
	@RequiresPermissions(value = {"sys:user:edit","sys:user:add"},logical= Logical.OR)
	@GetMapping("/toEdit")
	public ModelAndView toEdit(Model model,Integer id){
		User user = null;
		if(id!=null) {
			user = service.selectById(id);
		}else{
			user = new User();
		}
		List<Role> roleList = roleService.list(new HashMap<>());
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return new ModelAndView("complex/sys/user_edit");
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
	@Log("用户登录")
	@PostMapping("/login")
	public HashMap<String,Object> login(User user) {
		String password = MD5Util.encrypt(user.getName(), user.getPassword());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), password);
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
	@Log("用户退出")
	@GetMapping("/logout")
	public ModelAndView logout() {
		ShiroUtils.logout();
		return new ModelAndView("redirect:/login");
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
	@RequiresPermissions("sys:user:edit")
	@RequestMapping("/resetPwd")
	public HashMap<String,Object> resetPwd(UserVO userVO){
		try {
			service.resetPwd(userVO,getUser());
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	
	@Log("更新用户")
	@RequiresPermissions(value = {"sys:user:edit","sys:user:add"},logical= Logical.OR)
	@RequestMapping("/save")
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

	 @ApiOperation(value="用户列表", notes="用户列表接口")
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "userName", value = "根据用户名搜索", dataType = "String"),
	})
	@RequiresPermissions("sys:user:list")
	@PostMapping("/list")
	public HashMap<String,Object> list(Page page,String userName){
		Map<String,Object> map = page.getMap();
		if(userName!=null){
			map.put ("nameLike",userName);
		}
		List<User> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="删除用户", notes="删除用户接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "用户id", required = true ,dataType = "Integer")
    })
	@Log("删除用户")
	@RequiresPermissions("sys:user:delete")
	@PostMapping("/delete")
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
