package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Menu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
