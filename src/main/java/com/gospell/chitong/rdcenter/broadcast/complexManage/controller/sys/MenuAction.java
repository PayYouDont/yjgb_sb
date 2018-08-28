package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理")
@Controller
@RequestMapping("/menuAction")
public class MenuAction extends BaseAction{
	
	@Resource
	private MenuService service;
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("server",serverProperties);
		model.addAttribute("user",getUser());
		model.addAttribute("nvaMenuType","综合网络管理系统");
        return "common/index";
	}
	
	@GetMapping("/toList")
	public String toList(Model model) {
        return "complex/sys/menu_list";
	}
	@RequiresPermissions(value = {"sys:menu:edit","sys:menu:add"},logical= Logical.OR)
	@GetMapping("/toEdit")
	public String toAdd(Model model,Integer id) {
		Menu menu = null;
		if(id!=null) {
			menu = service.selectById(id);
		}else {
			menu = new Menu();
		}
		model.addAttribute("menu", menu);
        return "complex/sys/menu_edit";
	}
	
	@ApiOperation(value="菜单树", notes="菜单树接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "String")
    })
	@RequiresPermissions("sys:menu:list")
	@RequestMapping("/list")
	@ResponseBody
	public List<Menu> list(Integer roleId){
		List<Menu> menus = service.getTree(roleId);
		return menus;
	}
	
	@ApiOperation(value="保存菜单", notes="保存菜单接口")
	@Log("保存菜单")
	@RequiresPermissions(value = {"sys:menu:edit","sys:menu:add"},logical= Logical.OR)
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Menu menu){
		try {
			service.save(menu);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@ApiOperation(value="删除菜单", notes="删除菜单接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "菜单id",required=true,dataType = "String")
    })
	@Log("删除菜单")
	@RequiresPermissions("sys:menu:delete")
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			service.deleteById(id);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
