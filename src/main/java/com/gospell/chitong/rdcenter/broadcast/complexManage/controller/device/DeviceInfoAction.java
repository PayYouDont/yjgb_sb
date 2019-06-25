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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceParamVal;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceParamValService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@Api(tags = "设备信息管理")
@Controller
@RequestMapping("/deviceInfoAction")
public class DeviceInfoAction extends BaseAction {
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

	@RequiresPermissions(value = { "dev:info:add", "dev:info:edit" }, logical = Logical.OR)
	@GetMapping("/toEdit")
	public String toEdit(Model model, Integer id) {
		Deviceinfo deviceInfo = new Deviceinfo();
		if (id != null) {
			deviceInfo = service.selectById(id);
		}
		Map<String, Object> map = new HashMap<>();
		List<Devicemodel> deviceModelList = service.getDeviceModelList(map);
		model.addAttribute("deviceInfo", deviceInfo);
		model.addAttribute("deviceModelList", deviceModelList);
		return "complex/device/device_edit";
	}

	@GetMapping("/update")
	public String updateParam(Model model, Integer id) {
		Deviceinfo info = service.selectById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("deviceInfoId", info.getId());
		List<DeviceParamVal> params = dpvService.list(map);
		for (DeviceParamVal deviceParamVal : params) {
			deviceParamVal.setDeviceInfo(null);
		}
		model.addAttribute("params", params);
		model.addAttribute("deviceInfo", info);
		return "complex/device/device_param";
	}

	@RequiresPermissions("dev:info:edit")
	@GetMapping("/toRegist")
	public String goDeviceRegister(Model model, Integer id) {
		Deviceinfo dev = service.selectById(id);
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

	@ApiOperation(value="设备信息树", notes="设备信息树接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "行政区划code", required = true ,dataType = "String")
	})
	@PostMapping("/findByCodes")
	@ResponseBody
	public HashMap<String, Object> findByCodes(String code) {
		List<String> list = service.findByCodes(code);
		return JsonWrapper.successWrapper(list);
	}

	@ApiOperation(value="设备信息列表", notes="设备信息列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "devDsn", value = "搜索索引", dataType = "String"),
	})
	@RequestMapping("/list")
	@ResponseBody
	public HashMap<String, Object> list(Page page, String devDsn) {
		Map<String, Object> map = page.getMap();
		map.put("sort", "id");
		map.put("order", "ASC");
		if (devDsn != null) {
			map.put("devdsnLike", devDsn);
		}
		List<Deviceinfo> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="删除设备信息", notes="删除设备信息接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "设备信息id", required = true ,dataType = "String")
	})
	@Log("删除设备信息")
	@RequestMapping("/delete")
	@ResponseBody
	public HashMap<String, Object> delete(Integer id) {
		if (id == null) {
			return JsonWrapper.failureWrapper("id为空");
		}
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper(e.getMessage ());
		}
	}
    @ApiOperation(value="更新设备信息", notes="更新设备信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备信息id", required = true ,dataType = "String")
    })
    @Log("更新设备信息")
    @RequestMapping("/save")
    @ResponseBody
    public HashMap<String, Object> save(Deviceinfo deviceinfo) {
        try {
            service.update (deviceinfo);
            return JsonWrapper.successWrapper();
        } catch (Exception e) {
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper(e.getMessage ());
        }
    }
    @ApiOperation(value="保存设备信息", notes="保存设备信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备信息id", required = true ,dataType = "String")
    })
    @Log("保存设备信息")
    @RequestMapping("/regist")
    @ResponseBody
    public HashMap<String, Object> regist(Deviceinfo deviceinfo) {
        try {
            service.regist (deviceinfo);
            return JsonWrapper.successWrapper();
        } catch (Exception e) {
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper(e.getMessage ());
        }
    }
}
