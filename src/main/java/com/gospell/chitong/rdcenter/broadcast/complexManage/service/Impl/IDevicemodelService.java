package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DevicemodelService;
@Service
public class IDevicemodelService implements DevicemodelService{

	@Resource
	private DevicemodelMapper dao;

	@Override
	public List<Devicemodel> list(Map<String, Object> map) {
		return dao.list(map);
	}


}
