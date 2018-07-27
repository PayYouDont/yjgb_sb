package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuRoleRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuRoleRelationService;

@Service
public class IMenuRoleRelation implements MenuRoleRelationService{

	@Resource
	private MenuRoleRelationMapper dao;
	
	@Resource
	private MenuMapper menuDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int insert(MenuRoleRelation record) {
		int i = dao.insert(record);
		return i;
	}

	@Override
	public int insertSelective(MenuRoleRelation record) {
		int i = dao.insertSelective(record);
		return i;
	}

	@Override
	public MenuRoleRelation selectByPrimaryKey(Integer id) {
		MenuRoleRelation mrr = dao.selectByPrimaryKey(id);
		return mrr;
	}

	@Override
	public int updateByPrimaryKeySelective(MenuRoleRelation record) {
		int i = dao.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int updateByPrimaryKey(MenuRoleRelation record) {
		int i = dao.updateByPrimaryKey(record);
		return i;
	}

	@Override
	public List<MenuRoleRelation> findByRoleId(Integer roleId) {
		List<MenuRoleRelation> mrrs = dao.findByRoleId(roleId);
		return mrrs;
	}
	@Override
	public Set<String> getMenusByRoleId(Integer roleId) {
		List<MenuRoleRelation> mrrs = dao.findByRoleId(roleId);
		Set<String> menuNames = new HashSet<>();
		for (MenuRoleRelation mrr : mrrs) {
			Integer mid = mrr.getMenuId();
			Menu menu = menuDao.selectByPrimaryKey(mid);
			String menuUrl = menu.getUrl();
			menuNames.add(menuUrl);
		}
		return menuNames;
	}
}
