package com.gospell.chitong.rdcenter.broadcast.complexManage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.MenuRoleRelation;

public interface MenuRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRoleRelation record);

    int insertSelective(MenuRoleRelation record);

    MenuRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRoleRelation record);

    int updateByPrimaryKey(MenuRoleRelation record);
    
    List<MenuRoleRelation> findByRoleId(@Param("roleId")Integer roleId);
}