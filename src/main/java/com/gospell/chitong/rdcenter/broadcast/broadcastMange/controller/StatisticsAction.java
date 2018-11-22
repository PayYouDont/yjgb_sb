package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.StatisticsService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/statisticsAction")
public class StatisticsAction extends BaseAction{
	
	@Resource
	private StatisticsService service;
	
	@GetMapping("/toList")
	public String goStatistics() {
		return "broadcast/statistics";
	}
	
	/**
	 * 获取应急信息情况统计数据
	 * @Title: getStateData 
	 * @Description: TODO(获取应急信息情况统计数据) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 下午3:38:57
	 */
	@PostMapping("/getStateData")
	@ResponseBody
	public HashMap<String,Object> getStateData() {
		try {
			List<Map<String,Object>> list = service.getStateData();
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	/**
	 * 获取应急信息播发状态统计数据
	 * @Title: getStatusData 
	 * @Description: TODO(获取应急信息播发状态统计数据) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 下午3:39:10
	 */
	@PostMapping("/getStatusData")
	@ResponseBody
	public HashMap<String,Object> getStatusData() {
		try {
			List<Map<String,Object>> list = service.getStatusData();
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	/**
	 * 获取应急信息类型统计数据
	 * @Title: getTypeData 、
	 * @Description: TODO(获取应急信息类型统计数据) 
	 * @param @param parameter
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 下午3:39:36
	 */
	@PostMapping("/getTypeData")
	@ResponseBody
	public HashMap<String,Object> getTypeData(String parameter) {
		try {
			Map<String, Object> list = service.getTypeData(parameter);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
}
