package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AccidentLevelService;

@Service
public class IAccidentLevelService implements AccidentLevelService{
	
	@Resource AccidentlevelMapper dao;

	@Override
	public List<Accidentlevel> list(Map<String, Object> map) {
		return dao.list(map);
	}
	
}