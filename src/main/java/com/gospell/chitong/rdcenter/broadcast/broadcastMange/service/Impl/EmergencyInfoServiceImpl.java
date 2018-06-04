package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.util.List;
import java.util.Map;

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
	public int deleteById(Integer id) throws Exception {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int save(Emergencyinfo emer) throws Exception {
		int i = -1;
		if(emer.getId()!=null) {
			i = dao.updateByPrimaryKeySelective(emer);
		}else {
			i = dao.insert(emer);
		}
		return i;
	}

	@Override
	public Emergencyinfo selectById(Integer id) {
		Emergencyinfo emer = dao.selectByPrimaryKey(id);
		return emer;
	}

	@Override
	public List<Emergencyinfo> queryPage(Map<String, Object> map) {
		List<Emergencyinfo> list = dao.list(map);
		return list;
	}

	@Override
	public int countPage(Map<String, Object> map) {
		int i = dao.count(map);
		return i;
	}



	
}
