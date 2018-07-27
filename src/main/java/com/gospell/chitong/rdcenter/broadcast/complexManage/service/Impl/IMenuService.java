package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.MenuService;

@Service
public class IMenuService implements MenuService{

	@Resource
	private MenuMapper dao;
	
	@Override
	public int deleteById(Integer id) throws Exception{
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}


	@Override
	public Menu selectById(Integer id) {
		Menu menu = dao.selectByPrimaryKey(id);
		return menu;
	}

	@Override
	public List<Menu> getTree() {
		Map<String,Object> map = new HashMap<>();
		map.put("number", 0);
		List<Menu> pMenus= list(map);
		for (Menu menu : pMenus) {
			getChildren(menu);
		}
		return pMenus;
	}
	/**
	 * 递归设置Children
	 * @Title: getChildren 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param menu
	 * @param @return    设定文件 
	 * @return Menu    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月8日 上午9:39:21
	 */
	public Menu getChildren(Menu menu) {
		Integer pid = menu.getId();
		Map<String,Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("order", "number");
		map.put("sort", "ASC");
		List<Menu> children = list(map);
		menu.setChildren(children);
		if(children.size()>0) {
			for (Menu childmenu : children) {
				getChildren(childmenu);
			}
		}
		return menu;
	}

	@Override
	public List<Menu> list(Map<String, Object> map) {
		return dao.list(map);
	}


	@Override
	public int save(Menu menu) throws Exception{
		int i = 0;
		if(menu.getId()!=null) {
			i = dao.updateByPrimaryKeySelective(menu);
		}else {
			i = dao.insertSelective(menu);
		}
		return i;
	}
}
