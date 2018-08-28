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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.DevicemodelVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "设备型号管理")
@Controller
@RequestMapping("/deviceModelAction")
public class DeviceModelAction extends BaseAction{
	
	@Resource
	private DeviceModelService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/device/deviceModel_list";
	}
	@RequiresPermissions(value = { "dev:model:add", "dev:model:edit" }, logical = Logical.OR)
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
		return "complex/device/deviceModel_edit";
	}
	@ApiOperation(value="设备型号列表", notes="设备型号列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索", dataType = "String"),
	})
	@RequestMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page){
		Map<String,Object> map = page.getMap();
		map.put("sort","id");
		map.put("order", "ASC");
		List<Devicemodel> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="保存设备型号", notes="保存设备型号接口")
	@RequiresPermissions(value = { "dev:model:add", "dev:model:edit" }, logical = Logical.OR)
	@Log("保存设备型号")
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
	@ApiOperation(value="删除设备型号", notes="删除设备型号接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备型号id", required = true ,dataType = "String")
    })
	@RequiresPermissions("dev:model:delete")
	@Log("删除设备型号")
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
