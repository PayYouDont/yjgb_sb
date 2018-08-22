
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

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: AccidentTypeAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 上午9:40:09 
*  
*/
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
			accidentType = service.findById(id);
		}
		model.addAttribute("accidentType", accidentType);
		return "complex/param/accidentType_edit";
	}
	
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
	@RequiresPermissions(value = {"param:type:edit","param:type:add"},logical= Logical.OR)
	@PostMapping("/save")
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
	@RequiresPermissions("param:type:delete")
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
