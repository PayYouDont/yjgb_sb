package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.PlatformService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = "行政管理")
@RestController
@RequestMapping("/administrativeAction")
public class AdministrativeAction extends BaseAction{
	
	@Resource
	private AdministrativeService service;
	@Resource
    private PlatformService platformService;
	
	@GetMapping("/toList")
	public ModelAndView toList(Model model) {
		return new ModelAndView ("complex/param/administrative_list");
	}
	
	@ApiOperation(value="获取行政树", 
					notes="根据登录用户的行政区域编码获取该区域范围内的行政树接口")
	@RequestMapping("/getTreeByCode")
	public String getTreeByCode() {
		return service.getTreeStr(getUser().getAreaCode());
	}
	@ApiOperation(value="获取行政树", 
			notes="根据系统所在的行政区域编码获取该区域范围内的行政树接口")
	//getTreeBySystem
	@RequestMapping("/getTreeBySystem")
	public String getTreeBySystem() {
        Platform platform = platformService.selectById (1);
        if (platform!=null){
            return service.getTreeStr (platform.getAddressCode ());
        }
		//return service.getTreeStr(serverProperties.getAreaCode());
        return null;
	}
	@ApiOperation(value="行政区划列表", notes="行政区划接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "searchCondition", value = "搜索索引", dataType = "String")
    })
	@RequestMapping("/list")
	public String list(String searchCondition) {
		Map<String, Object> map = new HashMap<>();
		if(searchCondition!=null) {
			map.put("nameLike", searchCondition);
		}else {
			map.put("code",getUser().getAreaCode());
		}
		map.put("sort","code_level");
		map.put("order","ASC");
		List<Administrative> list = service.list(map);
		return JsonUtil.toJson(list);
	}
    @RequestMapping("/getListByCodeLevel")
    public String getListByCodeLevel(Integer level) {
        Map<String, Object> map = new HashMap<>();
        if(level!=null) {
            map.put("codeLevel", level);
        }
        map.put("sort","code_level");
        map.put("order","ASC");
        List<Administrative> list = service.listByCodeLevel(map);
        return JsonUtil.toJson(list);
    }
}
