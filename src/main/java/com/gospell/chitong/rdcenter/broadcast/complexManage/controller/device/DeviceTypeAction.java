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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceTypeService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "设备类型管理")
@Controller
@RequestMapping("deviceTypeAction")
public class DeviceTypeAction extends BaseAction{
	
	@Resource
	private DeviceTypeService service;
	
	@GetMapping("/toList")
	public String toList(Model model){
		return "complex/device/deviceType_list";
	}
	@RequiresPermissions(value = {"dev:type:add,dev:type:edit"},logical = Logical.OR)
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
	
	@ApiOperation(value="设备类型列表", notes="设备类型列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索", dataType = "String"),
	})
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page){
		Map<String,Object> map = page.getMap();
		List<Devicetype> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@RequiresPermissions(value = {"dev:type:add,dev:type:edit"},logical = Logical.OR)
	@Log("保存设备类型")
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
	
	@ApiOperation(value="事件等级列表", notes="事件等级列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备类型id", required = true ,dataType = "String")
    })
	@RequiresPermissions("dev:type:delete")
	@Log("删除设备类型")
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id){
		try {
			int i = service.delete(id);
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
