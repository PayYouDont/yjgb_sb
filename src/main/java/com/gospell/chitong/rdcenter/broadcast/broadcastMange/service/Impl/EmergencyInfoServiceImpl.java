package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;

@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService{

	@Resource
	private EmergencyinfoMapper dao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Emergencyinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Emergencyinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Emergencyinfo selectByPrimaryKey(Integer id) {
		Emergencyinfo emergencyinfo = dao.selectByPrimaryKey(id);
		return emergencyinfo;
	}

	@Override
	public int updateByPrimaryKeySelective(Emergencyinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Emergencyinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Emergencyinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
