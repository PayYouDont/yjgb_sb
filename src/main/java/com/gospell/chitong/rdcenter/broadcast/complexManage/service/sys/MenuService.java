package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

public interface MenuService {
	int deleteById(Integer id) throws Exception;

    Menu selectById(Integer id);
    
    List<Menu> getTree();
    
    List<Menu> list(Map<String,Object> map);
    
    int save(Menu menu) throws Exception;
}
