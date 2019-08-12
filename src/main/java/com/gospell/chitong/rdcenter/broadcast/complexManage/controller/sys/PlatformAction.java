package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info.EBD_EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info.EBD_EBRSTInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRPSState;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(tags = "平台管理")
@RestController
@RequestMapping("/platformAction")
public class PlatformAction extends BaseAction{
	@Resource
	private PlatformService service;
	
	@GetMapping("toPlatform")
	public ModelAndView toPlatform(Model model) {
		Platform platform = service.selectById(1);
		if (platform==null){
			platform = new Platform();
		}
		model.addAttribute("platform", platform);
		return new ModelAndView("complex/sys/platform");
	}
	@ApiOperation(value="平台信息保存", notes="平台信息保存接口")
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Platform platform){
		try {
			if(platform.getId()==null){
				platform.setId(1);//平台信息只能存在一个
			}
			int result = service.save(platform);
			EBD_EBRPSInfo info = new EBD_EBRPSInfo().createInstance(platform,"Sync");
			TarUtil.sendEBDToSuperior(info);
			EBD_EBRPSState state = new EBD_EBRPSState().createFullResponse();
			TarUtil.sendEBDToSuperior(state);
			if(result>0) {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return JsonWrapper.failureWrapper();
	}
}
