package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

@Controller
@RequestMapping("/administrativeAction")
public class AdministrativeAction extends BaseAction{
	
	@Resource
	public AdministrativeService service;
	
	@GetMapping("/toList")
	public String toList(Model model) {
		return "complex/param/administrative_list";
	}
	
	@RequestMapping("/getTreeByCode")
	@ResponseBody
	public String getTreeByCode() {
		return service.getTreeStr(getUser().getAreaCode());
	}
	
	//getTreeBySystem
	@RequestMapping("/getTreeBySystem")
	@ResponseBody
	public String getTreeBySystem() {
		return service.getTreeStr(serverProperties.getAreaCode());
	}
	@RequestMapping("/list")
	@ResponseBody
	public String list(String searchCondition) {
		Map<String, Object> map = new HashMap<>();
		if(searchCondition!=null) {
			map.put("nameLike", searchCondition);
		}else {
			map.put("code",getUser().getAreaCode());
		}
		map.put("sort","code_level");
		map.put("order","ASC");
		List<Administrative> list = service.list(map);
		return JsonUtil.toJsonArray(list);
	}
}
