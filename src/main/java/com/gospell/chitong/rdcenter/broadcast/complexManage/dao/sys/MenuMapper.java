package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.BaseDao;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

public interface MenuMapper extends BaseDao<Menu, Serializable>{

    List<Menu> findByText(@Param("text")String text);
    
    List<Menu> list(Map<String,Object> map);
    
    List<Menu> getRootMenu();
}