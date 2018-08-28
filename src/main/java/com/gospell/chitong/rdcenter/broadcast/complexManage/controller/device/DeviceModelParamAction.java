package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelParamService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "设备型号参数管理")
@Controller
@RequestMapping("/deviceModelParamAction")
public class DeviceModelParamAction extends BaseAction{
	
	@Resource
	private DeviceModelParamService service;
	
	@GetMapping("/toList")
	public String toList(Model model) {
		return "complex/device/deviceParam_list";
	}
	
	@RequiresPermissions(value= {"dev:param:add","dev:param:edit"},logical = Logical.OR)
	@GetMapping("/toEdit")
	public String toAdd(Model model,Integer id) {
		Devicemodelparam  dmp= null;
		if(id!=null) {
			dmp = service.findById(id);
		}else {
			dmp = new Devicemodelparam();
		}
		model.addAttribute("deviceModelParam", dmp);
		return "complex/device/deviceParam_edit";
	}
	
	@ApiOperation(value="设备型号参数列表", notes="设备型号参数列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索", dataType = "String"),
	})
	@RequestMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page){
		Map<String,Object> map = page.getMap();
		try {
			List<Devicemodelparam> list = service.list(map);
			int total = service.count(map);
			return JsonWrapper.wrapperPage(list, total);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	
	@ApiOperation(value="保存设备型号参数", notes="保存设备型号参数接口")
	@RequiresPermissions(value= {"dev:param:add","dev:param:edit"},logical = Logical.OR)
	@Log("保存设备型号参数")
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Devicemodelparam param){
		try {
			param.setCreateBy(getUserName());
			service.save(param);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	@ApiOperation(value="删除设备型号参数", notes="删除设备型号参数接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备型号参数id", required = true ,dataType = "String")
	})
	@RequiresPermissions("dev:param:delete")
	@Log("删除设备型号参数")
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("dmpId", id);
			List<DevModelParamRelation> list = service.findByMap(map);
			if(list.size()>0) {
				return JsonWrapper.failureWrapper("该型号参数正在使用");
			}
			service.deletById(id);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	
}
