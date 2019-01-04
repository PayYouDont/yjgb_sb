package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;

public interface MenuRoleRelationService extends BaseService<MenuRoleRelation,Integer>{
    List<MenuRoleRelation> findByRoleId(@Param("roleId")Integer roleId);
    
    Set<String> getRolePerms(Integer roleId);
}
