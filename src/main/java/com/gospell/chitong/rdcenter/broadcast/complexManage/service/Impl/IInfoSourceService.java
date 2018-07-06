package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.InfosourceMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.InfoSourceService;

@Service
public class IInfoSourceService implements InfoSourceService{
	
	@Resource
	private InfosourceMapper dao;
	@Override
	public List<Infosource> list(Map<String, Object> map) {
		return dao.list(map);
	}

}
