package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.RoleMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;

@Service
public class IRoleService implements RoleService{

	@Resource
	private RoleMapper dao;

	@Override
	public Role findById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int save(Role role) throws Exception {
		return role.getId()!=null?dao.updateByPrimaryKeySelective(role):dao.insertSelective(role);
	}

	@Override
	public List<Role> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
	
}
