package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Menu> menus = findByPid(id);
		SortUtil.MenuListSort(menus);
		return menus;
	}

	public List<Menu> findByPid(Integer pid){
		Map<String,Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("order", "number");
		map.put("sort", "ASC");
		List<Menu> list = menuDao.list(map);
		return list;
	}
	
	@Override
	public List<Menu> getNvaMenuByType(String type) throws Exception {
		//获取导航菜单主类型
		List<Menu> list = menuDao.findByText(type);
		if(list.size()==0) {
			return null;
		}
		//菜单数据
		Menu menuType = list.get(0);
		Integer pid = menuType.getId();
		//获取主类型子菜单
		List<Menu> menus = findByPid(pid);
		SortUtil.MenuListSort(menus);
		return menus;
	}



}
