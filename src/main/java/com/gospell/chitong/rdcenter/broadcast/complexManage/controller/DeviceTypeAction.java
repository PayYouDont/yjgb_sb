package com.gospell.chitong.rdcenter.broadcast.complexManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceTypeService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("deviceTypeAction")
public class DeviceTypeAction extends BaseAction{
	
	@Resource
	private DeviceTypeService service;
	
	@GetMapping("/toList")
	public String toList(Model model){
		return "complex/device/deviceType_list";
	}
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id){
		Devicetype deviceType = null;
		if(id!=null) {
			deviceType = service.selectById(id);
		}else {
			deviceType = new Devicetype();
		}
		model.addAttribute("deviceType", deviceType);
		return "complex/device/deviceType_edit";
	}
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page){
		Map<String,Object> map = page.getMap();
		List<Devicetype> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@RequestMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Devicetype deviceType){
		try {
			service.save(deviceType);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			int i = service.deleteById(id);
			if(i==-1) {
				logger.info("设备类型id="+id+"删除失败，该类型下拥有多个设备型号，请先删除这些型号数据！");
				return JsonWrapper.failureWrapper("该类型下拥有多个设备型号，请先删除这些型号数据！");
			}
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
