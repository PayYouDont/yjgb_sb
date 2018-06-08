package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;

public interface MenuService {
	int deleteById(Integer id) throws Exception;

    Menu selectById(Integer id);

    List<Menu> findByPid(@Param("pid")Integer pid);
    
    List<Menu> getTree();
    
    List<Menu> list(Map<String,Object> map);
    
    int save(Menu menu) throws Exception;
}
