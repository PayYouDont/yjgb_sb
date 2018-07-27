package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    List<Menu> findByText(@Param("text")String text);
    
    List<Menu> list(Map<String,Object> map);
}