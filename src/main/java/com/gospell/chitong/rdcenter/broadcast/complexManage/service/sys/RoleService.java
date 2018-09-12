package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.RoleMenuVO;

public interface RoleService extends BaseService<Role>{

	int save(RoleMenuVO vo) throws Exception;
	
}
