
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

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

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo.MediaResouceVO;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: MediaResouceAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月25日 下午4:53:17 
*  
*/
@RestController
@RequestMapping("mediaResouceAction")
public class MediaResouceAction extends BaseAction{
	@Resource
	private MediaResouceService service;
	
	@GetMapping("toList")
	public ModelAndView toList(Model model) {
		model.addAttribute("listType", "manage");
		return new ModelAndView("broadcast/mediaResouce_list");
	}
	@GetMapping("toReviewList")
	public ModelAndView toReviewList(Model model) {
		model.addAttribute("listType", "review");
		return new ModelAndView("broadcast/mediaResouce_list");
	}
	@PostMapping("list")
	public HashMap<String,Object> list(Page page,String listType,String search) {
		Map<String,Object> map = page.getMap();
		if("review".equals(listType)) {
			map.put("status", 0);
		}
		if(search!=null) {
			map.put("fileNameLike", search);
		}
		List<MediaResouce> list = service.list(map);
		int count = service.count(page.getMap());
		return JsonWrapper.wrapperPage(list,count);
	}
	
	
	@GetMapping("toEdit")
	public ModelAndView toEdit(Model model,Integer id,String type) {
		MediaResouce souce = new MediaResouce();
		if(id!=null) {
			souce = service.selectById(id);
		}
		model.addAttribute("souce", souce);
		model.addAttribute("type", type);//编辑或者审核
		return new ModelAndView("broadcast/mediaResouce_edit");
	}
	@PostMapping("save")
	public HashMap<String,Object> save(MediaResouceVO vo){
		try {
			service.save(vo);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
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
}
