package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import java.io.Serializable;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;

public interface UserMapper extends BaseDao<User, Serializable>{
	
	 User findByName(String name);
	 
}