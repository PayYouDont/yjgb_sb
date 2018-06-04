package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AdministrativeService;

public class IAdministrativeService implements AdministrativeService{
	
	@Resource
	private AdministrativeMapper dao;
	
	@Override
	public Administrative findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
