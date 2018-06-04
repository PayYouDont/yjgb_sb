package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceinfoService;

@Service
public class IDeviceinfoService implements DeviceinfoService{
	
	@Resource
	private DeviceinfoMapper dao;

	@Override
	public int save(Deviceinfo deviceinfo) throws Exception {
		if(deviceinfo.getId()!=null) {
			return dao.updateByPrimaryKeySelective(deviceinfo);
		}
		return dao.insertSelective(deviceinfo);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Deviceinfo findById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Deviceinfo> queryPage(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public List<Deviceinfo> findByCodes(String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		String [] codes = code.split(";");
		
		return null;
	}
	
}
