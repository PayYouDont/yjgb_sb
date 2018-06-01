package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.NvaMenuService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

/**
 * 
* @ClassName: NvaMenuAction 
* @Description: TODO(导航菜单管理) 
* @author peiyongdong
* @date 2018年5月31日 上午9:52:43 
*
 */
@Controller
@RequestMapping("/nvaMenuAction")
public class NvaMenuAction extends BaseAction{
	
	@Resource
	private NvaMenuService service;
	
	/**
	 * @Title: EmerNvaDetail 
	 * @Description: TODO(加载应急播发管理系统导航菜单数据,(主菜单)) 
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年5月31日 上午9:59:10
	 */
	@RequestMapping("/emerNvaDetail")
	@ResponseBody
	public HashMap<String,Object> EmerNvaDetail(String nvaMenuType) {
		try {
			List<Menu> menus = service.getNvaMenuByType(nvaMenuType);
			return JsonWrapper.successWrapper(menus);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
		
	}
	/**
	 * @Title: getMenuByPid 
	 * @Description: TODO(根据主菜单id获取一级菜单) 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 上午11:36:09
	 */
	@RequestMapping("/getMenuByPid")
	@ResponseBody
	public HashMap<String,Object> getMenuByPid(Integer pid){
		try {
			List<Menu> menus = service.getNvaMenuById(pid);
			return JsonWrapper.successWrapper(menus);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
}
