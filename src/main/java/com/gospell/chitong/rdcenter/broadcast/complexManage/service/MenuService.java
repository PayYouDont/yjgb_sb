package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;

public interface MenuService {
	int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> findByPid(@Param("pid")Integer pid);
}
