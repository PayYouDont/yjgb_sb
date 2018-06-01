package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.util.List;

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
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int insert(Emergencyinfo record) {
		int i = dao.insert(record);
		return i;
	}

	@Override
	public int insertSelective(Emergencyinfo record) {
		int i = dao.insertSelective(record);
		return i;
	}

	@Override
	public Emergencyinfo selectByPrimaryKey(Integer id) {
		Emergencyinfo emergencyinfo = dao.selectByPrimaryKey(id);
		return emergencyinfo;
	}

	@Override
	public int updateByPrimaryKeySelective(Emergencyinfo record) {
		int i = dao.updateByPrimaryKeySelective(record);
		return i;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Emergencyinfo record) {
		int i = dao.updateByPrimaryKeyWithBLOBs(record);
		return i;
	}

	@Override
	public int updateByPrimaryKey(Emergencyinfo record) {
		int i = dao.updateByPrimaryKey(record);
		return i;
	}
	
	public List<Emergencyinfo> queryByCode(String areaCode){
		
		return null;
	}
	
}
