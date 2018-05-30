package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;

public interface UserService {
	int deleteByPrimaryKey(Integer id) throws Exception;;

    int insert(User record) throws Exception;

    int insertSelective(User record) throws Exception;;

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record) throws Exception;;

    int updateByPrimaryKey(User record) throws Exception;
    
    User findByName(@Param("name")String name);
}
