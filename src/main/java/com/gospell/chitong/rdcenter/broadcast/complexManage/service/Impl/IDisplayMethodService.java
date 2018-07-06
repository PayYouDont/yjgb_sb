package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DisplayMethodService;

@Service
public class IDisplayMethodService implements DisplayMethodService{
	
	@Resource
	private DisplaymethodMapper dao;

	@Override
	public List<Displaymethod> list(Map<String, Object> map) {
		return dao.list(map);
	}
	
	
	
}
