package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
	
	@GetMapping("/toAdd")
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
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Menu> list(Integer roleId){
		List<Menu> menus = service.getTree(roleId);
		return menus;
	}
	
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
