package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;

@Controller
@RequestMapping("/emergencyInfoAction")
public class EmergencyInfoAction extends BaseAction{
	
	@Resource
	private EmergencyInfoService service;
	
	@Resource
	private ServerProperties server;
	
	@GetMapping("/index")  
    public String page(Model model){  
		model.addAttribute("server",server);
		model.addAttribute("user",getUser());
		model.addAttribute("nvaMenuType","应急播发管理系统");
        return "common/index";  
    }
	//信息管理
	@GetMapping("/goEmer") 
	public String goEmer(Model model) {
		model.addAttribute("emerType", "信息管理");
		return "broadcast/emer_list";
	}
	//信息审核
	@GetMapping("/goEmerReview") 
	public String goEmerReview(Model model) {
		model.addAttribute("emerType", "信息审核");
		return "broadcast/emer_list";
	}
	//信息播发
	@GetMapping("/goEmerBroadCast") 
	public String goEmerBroadCast(Model model) {
		model.addAttribute("emerType", "信息播发");
		return "broadcast/emer_list";
	}
	
	/**
	 * @Title: queryEmer 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午5:14:20
	 */
	public HashMap<String,Object> queryEmer(){
		User user = getUser();
		
		return null;
	}
}
