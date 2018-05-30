package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.MenuRoleRelation;

public interface MenuRoleRelationService {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRoleRelation record);

    int insertSelective(MenuRoleRelation record);

    MenuRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRoleRelation record);

    int updateByPrimaryKey(MenuRoleRelation record);
    
    List<MenuRoleRelation> findByRoleId(@Param("roleId")Integer roleId);
    
    Set<String> getMenusByRoleId(Integer roleId);
}
