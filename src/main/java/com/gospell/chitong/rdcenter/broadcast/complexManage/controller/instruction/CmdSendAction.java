/**   
* @Title: CmdSendAction.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年2月15日 上午11:38:58 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction;

import java.util.ArrayList;
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
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdSendService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: CmdSendAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年2月15日 上午11:38:58 
*  
*/
@RestController
@RequestMapping("/cmdSendAction")
public class CmdSendAction extends BaseAction{
	
	@Resource
	private CmdSendService service;
	@Resource
	private CmdConfigService cmdConfigService;
	
	@GetMapping("toList")
	public ModelAndView toList() {
		return new ModelAndView("complex/instruction/cmdSend_list");
	}
	@PostMapping("list")
	public HashMap<String,Object> list(Page page,String search) {
		Map<String,Object> map = page.getMap();
		if(search!=null) {
			map.put("nameLike", search);
			List<CmdConfig> configs = new ArrayList<>();
			configs = cmdConfigService.list(map);
			if(configs.size()>0) {
				List<Integer> typeList = new ArrayList<>();
				for (CmdConfig config : configs) {
					typeList.add(config.getId());
				}
				map.put("typeList", typeList);
			}
		}
		map.put("sort", "id");
		map.put("order", "DESC");
		List<CmdSend> list = service.list(map);
		int count = service.count(map);
		return JsonWrapper.wrapperPage(list, count);
	}
	@GetMapping("toEdit")
	public ModelAndView toEdit(Model model,Integer id) {
		CmdSend cmdSend = null;
		if(id!=null) {
			cmdSend = service.selectById(id);
		}
		if(cmdSend == null) {
			cmdSend = new CmdSend();
		}
		model.addAttribute("cmdSend",cmdSend);
		model.addAttribute("cmdConfigList",cmdConfigService.list(new HashMap<>()));
		return new ModelAndView("complex/instruction/cmdSend_edit");
	}
	@PostMapping("delete")
	public HashMap<String,Object> delete(Integer[] ids){
		try {
			service.delete(ids);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("save")
	public HashMap<String,Object> save(CmdSend cmdSend){
		try {
			service.save(cmdSend);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@PostMapping("send")
	public HashMap<String,Object> send(Integer[] ids){
		try {
			Integer status = service.send(ids);
			if(status==200){
			    return JsonWrapper.successWrapper ();
            }else{
			    return JsonWrapper.failureWrapper ();
            }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
