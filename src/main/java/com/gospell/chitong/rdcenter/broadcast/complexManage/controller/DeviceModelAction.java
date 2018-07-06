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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.DevicemodelVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/deviceModelAction")
public class DeviceModelAction extends BaseAction{
	
	@Resource
	private DeviceModelService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/deviceModel_list";
	}
	
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id) {
		Devicemodel deviceModel = null;
		String deviceModelParamIds="";
		if(id!=null) {
			deviceModel = service.selectById(id);
			Map<String, Object> map = new HashMap<>();
			map.put("dmId", id);
			List<DevModelParamRelation> list = service.devModelParamRelationList(map);
			for (DevModelParamRelation dmpr : list) {
				deviceModelParamIds += dmpr.getDmpId()+",";
			}
		}else {
			deviceModel = new Devicemodel();
		}
		Map<String, Object> map = new HashMap<>();
		List<Devicemodelparam> deviceModelParamList = service.deviceModelParamList(map);
		List<Devicetype> deviceTypeList = service.deviceTypeList(map);
		model.addAttribute("deviceModel", deviceModel);
		model.addAttribute("deviceModelParamList", deviceModelParamList);
		model.addAttribute("deviceTypeList", deviceTypeList);
		model.addAttribute("deviceModelParamIds", deviceModelParamIds);
		return "complex/deviceModel_edit";
	}
	@RequestMapping("/queryList")
	@ResponseBody
	public HashMap<String,Object> queryList(Page page){
		Map<String,Object> map = page.getMap();
		map.put("sort","id");
		map.put("order", "ASC");
		List<Devicemodel> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String, Object> save(DevicemodelVO vo){
		Devicemodel model = vo.getDevicemodel();
		try {
			service.save(model);
			//删除关系表
			service.deleteDMPRByDevModelId(model.getId());
			//保存新的关系
			service.saveDMPR(model.getId(),vo.getDeviceModelParamIds());
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error("保存设备型号失败!",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
		
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			int result = service.delete(id);
			if(result==-1) {
				return JsonWrapper.failureWrapper("该型号下拥有多个设备，请先删除这些设备数据！");
			}
			service.deleteDMPRByDevModelId(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("删除设备型号失败!",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
