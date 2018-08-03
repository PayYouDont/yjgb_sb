package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.RoleMenuVO;

import java.util.List;
import java.util.Map;

public interface RoleService {
	
	Role findById(Integer id);
	
	int delete(Integer id) throws Exception;
	
	int save(RoleMenuVO vo) throws Exception;
	
	List<Role> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}
