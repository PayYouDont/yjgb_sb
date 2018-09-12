package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuRoleRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuRoleRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class IMenuRoleRelation implements MenuRoleRelationService{

	@Resource
	private MenuRoleRelationMapper dao;
	
	@Resource
	private MenuMapper menuDao;
	
	@Override
	public int delete(Integer id) {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}
	
	@Override
	public int save(MenuRoleRelation record) {
		if(record.getId()==null) {
			return dao.insertSelective(record);
		}else {
			return dao.updateByPrimaryKeySelective(record);
		}
	}

	@Override
	public MenuRoleRelation selectById(Integer id) {
		MenuRoleRelation mrr = dao.selectByPrimaryKey(id);
		return mrr;
	}
	@Override
	public List<MenuRoleRelation> findByRoleId(Integer roleId) {
		Map<String,Object> map = new HashMap<> ();
		map.put("roleId",roleId);
		List<MenuRoleRelation> mrrs = dao.list (map);
		return mrrs;
	}
	@Override
	public Set<String> getRolePerms(Integer roleId) {
		Map<String,Object> map = new HashMap<> ();
		map.put("roleId",roleId);
		List<MenuRoleRelation> mrrs = dao.list (map);
		Set<String> permsSet = new HashSet<>();
		for (MenuRoleRelation mrr : mrrs) {
			Integer mid = mrr.getMenuId();
			Menu menu = menuDao.selectByPrimaryKey(mid);
			String perms = menu.getPerms();
			permsSet.addAll(mrr.getPermsSet(perms));
		}
		return permsSet;
	}
}
