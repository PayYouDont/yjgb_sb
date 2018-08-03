package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.PlatformVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/platformAction")
public class PlatformAction extends BaseAction{

	@Resource
	private PlatformService service;
	
	@GetMapping("toPlatform")
	public String toPlatform(Model model) {
		model.addAttribute("server", serverProperties);
		return "complex/sys/platform";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(PlatformVO vo){
		try {
			int result = service.saveAndUpdate(vo);
			if(result>0) {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return JsonWrapper.failureWrapper();
	}
}
