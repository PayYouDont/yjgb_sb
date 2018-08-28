
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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 
* @ClassName: AccidentLevelAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 上午11:50:39 
*  
*/
@Api(tags = "事件级别管理")
@Controller
@RequestMapping("/accidentLevelAction")
public class AccidentLevelAction extends BaseAction{
	@Resource
	private AccidentLevelService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/param/accidentLevel_list";
	}
	@GetMapping("/toEdit")
	@RequiresPermissions(value = {"param:level:edit","param:level:add"},logical= Logical.OR)
	public String toEdit(Model model,Integer id) {
		Accidentlevel accidentLevel = new Accidentlevel();
		if(id!=null) {
			accidentLevel = service.findById(id);
		}
		model.addAttribute("accidentLevel", accidentLevel);
		return "complex/param/accidentLevel_edit";
	}
	@ApiOperation(value="事件等级列表", notes="事件等级列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索", dataType = "String"),
	})
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page,String search) {
		Map<String,Object> map = page.getMap();
		map.put("sort", "number");
		map.put("order", "ASC");
		if(search!=null) {
			map.put("levelLike", search);
		}
		List<Accidentlevel> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="保存事件等级", notes="保存事件等级接口")
	@RequiresPermissions(value = {"param:level:edit","param:level:add"},logical= Logical.OR)
	@Log("保存事件等级")
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Accidentlevel level) {
		try {
			int i = service.save(level);
			String msg = "";
			if(i==-1) {
				msg = "修改失败,该等级正在被多个信息使用";
			}else if(i==-2) {
				msg = "事件等级名称已经存在";
			}else if(i==-3) {
				msg = "事件等级编码已经存在";
			}
			if(!"".equals(msg)) {
				return JsonWrapper.failureWrapper(msg);
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("保存事件等级错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@ApiOperation(value="删除事件等级", notes="删除事件等级接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "角色id",required=true,dataType = "String")
    })
	@Log("删除事件等级")
	@RequiresPermissions("param:level:delete")
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id) {
		if(id==null) {
			return JsonWrapper.failureWrapper("该事件等级不存在");
		}
		try {
			int i = service.delete(id);
			if(i==-1) {
				return JsonWrapper.failureWrapper("删除失败,该等级正在被多个信息使用");
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("删除事件等级失败！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
