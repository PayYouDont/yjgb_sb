package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.NvaMenuService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;
import com.gospell.chitong.rdcenter.broadcast.util.SortUtil;

@Service
public class INvaMenuService implements NvaMenuService{

	@Resource
	private MenuMapper menuDao;
	
	@Override
	public List<Menu> getNvaMenuById(Integer id) throws Exception{
		List<Menu> menus = menuDao.findByPid(id);
		SortUtil.MenuListSort(menus);
		return menus;
	}

	@Override
	public List<Menu> getNvaMenuByType(String type) throws Exception {
		//获取导航菜单主类型
		List<Menu> list = menuDao.findByText(type);
		Menu menuType = list.get(0);
		//菜单数据
		List<Menu> menus = null;
		if(menuType!=null) {
			Integer pid = menuType.getId();
			//获取主类型子菜单
			menus = menuDao.findByPid(pid);
		}
		SortUtil.MenuListSort(menus);
		return menus;
	}



}
