package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.sys;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.UserMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;
import com.gospell.chitong.rdcenter.broadcast.util.MD5Util;
@Service
public class IUserService implements UserService{

	@Resource
	private UserMapper dao;
	
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
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}



	@Override
	public User selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}



	@Override
	public User findByName(String name) {
		return dao.findByName(name);
	}



	@Override
	public int save(User user) throws Exception{
		return user.getId() != null ? dao.updateByPrimaryKeySelective(user) : dao.insertSelective(user);
	}



	@Override
	public List<User> list(Map<String, Object> map) {
		return dao.list(map);
	}



	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
