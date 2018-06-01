package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;

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
	public int deleteByPrimaryKey(Integer id) {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int insert(Menu record) {
		int i = dao.insert(record);
		return i;
	}

	@Override
	public int insertSelective(Menu record) {
		int i = dao.insertSelective(record);
		return i;
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		Menu menu = dao.selectByPrimaryKey(id);
		return menu;
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		int i = dao.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		int i = dao.updateByPrimaryKey(record);
		return i;
	}

	@Override
	public List<Menu> findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}
}
