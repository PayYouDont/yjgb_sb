package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AccidentTypeSevice;

@Service
public class IAccidentTypeService implements AccidentTypeSevice{
	
	@Resource
	private AccidenttypeMapper dao;

	@Override
	public List<Accidenttype> list(Map<String, Object> map) {
		return dao.list(map);
	}
	
	
	
}
