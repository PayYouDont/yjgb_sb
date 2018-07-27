package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.device;

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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceParamVal;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceParamValService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/deviceInfoAction")
public class DeviceInfoAction extends BaseAction{
	@Resource
	private DeviceInfoService service;
	
	@Resource
	private DeviceModelService dmService;
	
	@Resource
	private DeviceParamValService dpvService;
	
	@GetMapping("/toList")
	public String toDevList() {
		return "complex/device/device_list";
	}
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id) {
		Deviceinfo deviceInfo = new Deviceinfo();
		if(id!=null) {
			deviceInfo = service.findById(id);
		}else {
			deviceInfo = new Deviceinfo();
		}
		Map<String,Object> map = new HashMap<>();
		List<Devicemodel> deviceModelList = service.getDeviceModelList(map);
		model.addAttribute("deviceInfo",deviceInfo);
		model.addAttribute("deviceModelList", deviceModelList);
		return "complex/device/device_edit";
	}
	@GetMapping("/updateParam")
	public String updateParam(Model model,Integer id) {
		Deviceinfo info = service.findById(id);
		Map<String,Object> map = new HashMap<>();
		map.put("deviceInfoId", info.getId());
		List<DeviceParamVal> params = dpvService.list(map);
		for (DeviceParamVal deviceParamVal : params) {
			deviceParamVal.setDeviceInfo(null);
		}
		model.addAttribute("params", params);
		model.addAttribute("deviceInfo", info);
		return "complex/device/device_param";
	}
	
	@GetMapping("/goDeviceRegister")
	public String goDeviceRegister(Model model,Integer id) {
		Deviceinfo dev = service.findById(id);
		List<Devicemodel> deviceModelList = dmService.list(new HashMap<>());
		model.addAttribute("device", dev);
		model.addAttribute("deviceModelList", deviceModelList);
		return "complex/device/device_regist";
	}
	@GetMapping("/goCoordinate")
	public String goMap(Model model) {
		model.addAttribute("unit_json", serverProperties.getLocation());
		return "common/coordinate";
	}
	@GetMapping("/goTree")
	public String goTree() {
		return "common/areaTree";
	}
	@PostMapping("/findByCodes")
	@ResponseBody
	public HashMap<String,Object> findByCodes(String code){
		List<String> list = service.findByCodes(code);
		return JsonWrapper.successWrapper(list);
	}
	@RequestMapping("/queryList")
	@ResponseBody
	public HashMap<String,Object> queryList(Page page){
		Map<String,Object> map = page.getMap();
		map.put("sort","id");
		map.put("order", "ASC");
		List<Deviceinfo> list = service.list(map);
		int total = service.queryCount(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		if(id==null) {
			return JsonWrapper.failureWrapper("id为空");
		}
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e);
		}
	}
	
}
