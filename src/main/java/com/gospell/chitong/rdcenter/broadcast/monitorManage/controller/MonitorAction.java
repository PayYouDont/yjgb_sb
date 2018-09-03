
package com.gospell.chitong.rdcenter.broadcast.monitorManage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/** 
* @ClassName: MonitorAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月3日 上午10:17:46 
*  
*/
@RestController
@RequestMapping("monitorAction")
public class MonitorAction {
	
	@GetMapping("index")
	public ModelAndView toIndex() {
		return new ModelAndView("monitor/index");
	}
	
}
