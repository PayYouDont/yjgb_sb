
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.param;

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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 
* @ClassName: DisplayMethodAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 下午4:05:10 
*  
*/
@Api(tags = "播发方式管理")
@Controller
@RequestMapping("/displayMethodAction")
public class DisplayMethodAction extends BaseAction{
	@Resource
	private DisplayMethodService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/param/displayMethod_list";
	}
	@RequiresPermissions(value = {"param:method:edit","param:method:add"},logical= Logical.OR)
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id) {
		Displaymethod displayMethod = new Displaymethod();
		if(id!=null) {
			displayMethod = service.selectById(id);
		}
		model.addAttribute("displayMethod", displayMethod);
		return "complex/param/displayMethod_edit";
	}
	
	@ApiOperation(value="播发方式列表", notes="播发方式列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索索引", dataType = "String")
    })
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page,String search) {
		Map<String, Object> map = page.getMap();
		map.put("sort", "number");
		map.put("order", "ASC");
		if(search!=null) {
			map.put("methodLike", search);
		}
		List<Displaymethod> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	
	@ApiOperation(value="保存播发方式", notes="保存播发方式接口")
	@RequiresPermissions(value = {"param:method:edit","param:method:add"},logical= Logical.OR)
	@Log("保存播发方式")
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Displaymethod method) {
		try {
			int i = service.save(method);
			String msg = "";
			if(i==-2) {
				msg = "播发方式名称已经存在";
			}else if(i==-3) {
				msg = "播发方式编码已经存在";
			}
			if(!"".equals(msg)) {
				return JsonWrapper.failureWrapper(msg);
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("保存播发方式错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@ApiOperation(value="删除播发方式", notes="删除播发方式接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "播发方式id",required=true, dataType = "String")
    })
	@RequiresPermissions("param:method:delete")
	@PostMapping("/delete")
	@Log("删除播发方式")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id) {
		if(id==null) {
			return JsonWrapper.failureWrapper("该播发方式不存在");
		}
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("删除播发方式错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
