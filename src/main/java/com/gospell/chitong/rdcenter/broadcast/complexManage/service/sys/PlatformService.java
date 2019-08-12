package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Platform;

import java.util.List;

public interface PlatformService extends BaseService<Platform,Integer> {
	List<Platform> findAll();
}
