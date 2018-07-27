package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}