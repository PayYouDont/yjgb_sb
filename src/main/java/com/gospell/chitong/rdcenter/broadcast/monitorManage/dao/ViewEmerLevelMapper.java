package com.gospell.chitong.rdcenter.broadcast.monitorManage.dao;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.monitorManage.entity.ViewEmerLevel;

public interface ViewEmerLevelMapper {
	
	List<ViewEmerLevel> countByDate(Map<String,Object> map);
}