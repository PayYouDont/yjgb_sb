package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.UserService;
@Service
public class IUserService implements UserService{

	@Resource
	private UserMapper dao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int insert(User record) throws Exception{
		int i = dao.insert(record);
		return i;
	}

	@Override
	public int insertSelective(User record) throws Exception{
		int i = dao.insertSelective(record);
		return i;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		User user = dao.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) throws Exception{
		int i = dao.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int updateByPrimaryKey(User record) throws Exception{
		int i = dao.updateByPrimaryKey(record);
		return i;
	}

	@Override
	public User findByName(String name) {
		User user = dao.findByName(name);
		return user;
	}

}
