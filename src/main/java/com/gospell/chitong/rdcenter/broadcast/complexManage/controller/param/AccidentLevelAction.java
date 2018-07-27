
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: AccidentLevelAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月27日 上午11:50:39 
*  
*/
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
	public String toEdit(Model model,Integer id) {
		Accidentlevel accidentLevel = new Accidentlevel();
		if(id!=null) {
			accidentLevel = service.findById(id);
		}
		model.addAttribute("accidentLevel", accidentLevel);
		return "complex/param/accidentLevel_edit";
	}
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