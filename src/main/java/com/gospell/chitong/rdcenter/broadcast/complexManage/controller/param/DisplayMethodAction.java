
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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: DisplayMethodAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 下午4:05:10 
*  
*/
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
			displayMethod = service.findById(id);
		}
		model.addAttribute("displayMethod", displayMethod);
		return "complex/param/displayMethod_edit";
	}
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
	@RequiresPermissions(value = {"param:method:edit","param:method:add"},logical= Logical.OR)
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
	@RequiresPermissions("param:method:delete")
	@PostMapping("/delete")
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
