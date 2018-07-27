package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;

public interface RoleService {
	
	Role findById(Integer id);
	
	int delete(Integer id) throws Exception;
	
	int save(Role role) throws Exception;
	
	List<Role> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
