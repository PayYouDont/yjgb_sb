/**   
* @Title: CmdConfigAction.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月17日 下午5:42:51 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: CmdConfigAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月17日 下午5:42:51 
*  
*/
@RestController
@RequestMapping("cmdConfigAction")
public class CmdConfigAction extends BaseAction{
	@Resource
	private CmdConfigService service;
	
	@GetMapping("toList")
	public ModelAndView toList() {
		return new ModelAndView("complex/instruction/cmdConfig_list");
	}
	@PostMapping("list")
	public HashMap<String,Object> list(Page page){
		Map<String,Object> map = page.getMap ();
		List<CmdConfig> list = service.list(map);
		int count = service.count(map);
		return JsonWrapper.wrapperPage(list, count);
	}
	@GetMapping("toEdit")
	public ModelAndView toEdit(Model model,Integer id) {
		CmdConfig cmd = null;
		if(id!=null) {
			cmd = service.selectById(id);
		}
		if(cmd == null) {
			cmd = new CmdConfig();
		}
		model.addAttribute("cmd",cmd);
		return new ModelAndView("complex/instruction/cmdConfig_edit");
	}
	@PostMapping("delete")
	public HashMap<String,Object> delete(Integer id){
		try {
			service.delete(id);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("save")
	public HashMap<String,Object> save(CmdConfig cmd){
		try {
			service.save(cmd);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
