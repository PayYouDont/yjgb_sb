package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
	int deleteById(Integer id) throws Exception;

    Menu selectById(Integer id);
    
    List<Menu> getTree(Integer roleId);
    
    List<Menu> list(Map<String,Object> map);
    
    int save(Menu menu) throws Exception;
}
