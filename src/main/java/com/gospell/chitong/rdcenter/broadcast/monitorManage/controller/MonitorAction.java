
package com.gospell.chitong.rdcenter.broadcast.monitorManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.monitorManage.controller.vo.StatisticsVO;
import com.gospell.chitong.rdcenter.broadcast.monitorManage.service.MonitorService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/** 
* @ClassName: MonitorAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月3日 上午10:17:46 
*  
*/
@RestController
@RequestMapping("monitorAction")
public class MonitorAction extends BaseAction{
	
	@Resource
	private MonitorService service;
	
	@GetMapping("index")
	public ModelAndView toIndex() {
		return new ModelAndView("monitor/index");
	}
	@PostMapping("/getData")
	@ResponseBody
	public HashMap<String,Object> getData(StatisticsVO vo) {
		try {
			List<?> list = service.getData(vo);
			System.out.println(list);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
}
