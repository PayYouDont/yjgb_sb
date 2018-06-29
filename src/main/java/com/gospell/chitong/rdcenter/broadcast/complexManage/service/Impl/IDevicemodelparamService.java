package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevModelParamRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelparamMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DevicemodelparamService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IDevicemodelparamService implements DevicemodelparamService{
	
	@Resource
	private DevicemodelparamMapper dao;

	@Resource
	private DevModelParamRelationMapper dmprdao;
	
	@Override
	public List<Devicemodelparam> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public int save(Devicemodelparam device) throws Exception{
		int i = 0;
		if(device.getId()!=null) {
			device.setUpdateBy(ShiroUtils.getUser().getName());
			i = dao.updateByPrimaryKeySelective(device);
		}else {
			device.setCreateBy(ShiroUtils.getUser().getName());
			i = dao.insertSelective(device);
		}
		return i;
	}

	@Override
	public int deletById(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<DevModelParamRelation> findByMap(Map<String, Object> map) {
		return dmprdao.list(map);
	}
	
	
}
