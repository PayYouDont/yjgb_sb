package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaylanguageMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DisplayLanguageService;

@Service
public class IDisplaylanguageService implements DisplayLanguageService{

	@Resource
	private DisplaylanguageMapper dao;

	@Override
	public List<Displaylanguage> list(Map<String, Object> map) {
		return dao.list(map);
	}
	
	
}
