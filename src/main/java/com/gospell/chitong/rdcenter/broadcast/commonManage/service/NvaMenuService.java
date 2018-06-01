package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Menu;

public interface NvaMenuService {
	
	public List<Menu> getNvaMenuById(Integer id) throws Exception;
	
	public List<Menu> getNvaMenuByType(String type) throws Exception;
}
