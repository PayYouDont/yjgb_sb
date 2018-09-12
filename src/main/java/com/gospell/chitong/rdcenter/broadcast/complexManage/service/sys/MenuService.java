package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;

public interface MenuService extends BaseService<Menu>{
    List<Menu> getTree(Integer roleId);
}
