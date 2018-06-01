package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.UserMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.UserService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
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

	@Override
	public int resetPwd(UserVO userVO, User user) throws Exception {
		if(Objects.equals(userVO.getUserId(),user.getId())) {
			if(Objects.equals(MD5Util.encrypt(user.getName(),userVO.getOldPwd()),user.getPassword())) {
				user.setPassword(MD5Util.encrypt(user.getName(),userVO.getNewPwd()));
				return dao.updateByPrimaryKeySelective(user);
			}else {
				throw new Exception("输入的旧密码有误！");
			}
		}else {
			throw new Exception("你修改的不是你登录的账号！");
		}
	}

	@Override
	public int updateUser(User user) throws Exception {
		//User sessionUser = ShiroUtils.getUser();
		
		return 0;
	}

}
