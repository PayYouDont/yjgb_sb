
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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/** 
* @ClassName: AccidentTypeAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 上午9:40:09 
*/

@Api(tags="事件类型管理")
@Controller
@RequestMapping("/accidentTypeAction")
public class AccidentTypeAction extends BaseAction{
	
	@Resource
	private AccidentTypeSevice service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/param/accidentType_list";
	}
	@RequiresPermissions(value = {"param:type:edit","param:type:add"},logical= Logical.OR)
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id) {
		Accidenttype accidentType = new Accidenttype();
		if(id!=null) {
			accidentType = service.selectById(id);
		}
		model.addAttribute("accidentType", accidentType);
		return "complex/param/accidentType_edit";
	}
	@ApiOperation(value="事件类别列表", notes="事件类别接口")
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
			map.put("nameLike",search);
		}
		List<Accidenttype> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="保存事件类别", notes="保存事件类别接口")
	@RequiresPermissions(value = {"param:type:edit","param:type:add"},logical= Logical.OR)
	@PostMapping("/save")
	@Log("保存事件类别")
	@ResponseBody
	public HashMap<String,Object> save(Accidenttype accidenttype) {
		try {
			int i = service.save(accidenttype);
			String msg = "";
			if(i==-1) {
				msg = "修改失败,该类型正在被多个信息使用";
			}else if(i==-2) {
				msg = "事件类型名称已经存在";
			}else if(i==-3) {
				msg = "事件编号已经存在";
			}
			if(!"".equals(msg)) {
				return JsonWrapper.failureWrapper(msg);
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("保存事件错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@ApiOperation(value="删除事件类别", notes="删除事件类别接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "事件类别id",required=true, dataType = "String")
    })
	@RequiresPermissions("param:type:delete")
	@Log("删除事件类别")
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id) {
		if(id==null) {
			return JsonWrapper.failureWrapper("该事件不存在");
		}
		try {
			int i = service.delete(id);
			if(i==-1) {
				return JsonWrapper.failureWrapper("删除失败,该类型正在被多个信息使用");
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("删除事件失败！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
