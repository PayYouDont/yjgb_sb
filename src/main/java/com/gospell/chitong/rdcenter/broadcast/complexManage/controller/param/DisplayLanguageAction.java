
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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: DisplayLanguageAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 下午3:02:53 
*  
*/
@Controller
@RequestMapping("/displayLanguageAction")
public class DisplayLanguageAction extends BaseAction{
	
	@Resource
	private DisplayLanguageService service;
	
	@GetMapping("/toList")
	public String toList() {
		return "complex/param/displayLanguage_list";
	}
	@RequiresPermissions(value = {"param:language:edit","param:language:add"},logical= Logical.OR)
	@GetMapping("/toEdit")
	public String toEdit(Model model,Integer id) {
		Displaylanguage displayLanguage = new Displaylanguage();
		if(id!=null) {
			displayLanguage = service.findById(id);
		}
		model.addAttribute("displayLanguage", displayLanguage);
		return "complex/param/displayLanguage_toEdit";
	}
	@PostMapping("/list")
	@ResponseBody
	public HashMap<String,Object> list(Page page,String search) {
		Map<String, Object> map = page.getMap();
		map.put("sort", "number");
		map.put("order", "ASC");
		if(search!=null) {
			map.put("languageLike", search);
		}
		List<Displaylanguage> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@RequiresPermissions(value = {"param:language:edit","param:language:add"},logical= Logical.OR)
	@PostMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Displaylanguage language) {
		try {
			int i = service.save(language);
			String msg = "";
			if(i==-2) {
				msg = "播发语言名称已经存在";
			}else if(i==-3) {
				msg = "播发方式简称已经存在";
			}
			if(!"".equals(msg)) {
				return JsonWrapper.failureWrapper(msg);
			}else {
				return JsonWrapper.successWrapper();
			}
		} catch (Exception e) {
			logger.error("保存播发语言错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@RequiresPermissions("param:language:delete")
	@PostMapping("/delete")
	@ResponseBody
	public HashMap<String,Object> delete(Integer id) {
		if(id==null) {
			return JsonWrapper.failureWrapper("该播发语言不存在");
		}
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("删除播发语言错误！",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
