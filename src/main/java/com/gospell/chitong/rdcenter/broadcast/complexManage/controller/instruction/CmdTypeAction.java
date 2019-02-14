/**   
* @Title: CmdTypeAction.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月25日 下午3:24:58 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdTypeService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: CmdTypeAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月25日 下午3:24:58 
*  
*/
@RestController
@RequestMapping("cmdTypeAction")
public class CmdTypeAction extends BaseAction{
	
	@Resource
	private CmdTypeService service;
	@GetMapping("toList")
	public ModelAndView toList(Model model) {
		return new ModelAndView("/complex/instruction/cmdType_list");
	}
	@PostMapping("list")
	public HashMap<String,Object> list(Page page) {
		Map<String,Object> map = page.getMap();
		List<CmdType> list = service.list(map);
		int count = service.count(map);
		return JsonWrapper.wrapperPage(list, count);
	}
	@PostMapping("listAll")
	public HashMap<String,Object> listAll() {
		Map<String,Object> map = new HashMap<>();
		List<CmdType> list = service.list(map);
		return JsonWrapper.successWrapper(list);
	}
	@GetMapping("toEdit")
	public ModelAndView toEdit(Model model,Integer id) {
		CmdType cmdType = null;
		if(id!=null){
			cmdType = service.selectById(id);
		}
		if(cmdType==null) {
			cmdType = new CmdType();
		}
		model.addAttribute("cmdType", cmdType);
		return new ModelAndView("/complex/instruction/cmdType_edit");
	}
	@PostMapping("save")
	public HashMap<String,Object> save(CmdType cmdType) {
		try {
			service.save(cmdType);
			return  JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("get")
	public HashMap<String,Object> get(Integer id) {
		try {
			CmdType cmdType = service.selectById(id);
			String sourceId  = cmdType.getSourceUrl();
			if(sourceId!=null) {
				Integer menuId = Integer.parseInt(sourceId);
				Menu menu = ApplicationContextRegister.getBean(MenuService.class).selectById(menuId);
				if(menu!=null) {
					cmdType.setSourceUrl(menu.getUrl());
				}
			}
			return JsonWrapper.successWrapper(cmdType);
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("delete")
	public HashMap<String,Object> delete(Integer id) {
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
