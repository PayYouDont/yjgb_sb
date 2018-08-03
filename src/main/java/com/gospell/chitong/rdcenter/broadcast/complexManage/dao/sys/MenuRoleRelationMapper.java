package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;

import java.util.List;
import java.util.Map;

public interface MenuRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRoleRelation record);

    int insertSelective(MenuRoleRelation record);

    MenuRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRoleRelation record);

    int updateByPrimaryKey(MenuRoleRelation record);

    List<MenuRoleRelation> list(Map<String,Object> map);

    int count(Map<String,Object> map);

}