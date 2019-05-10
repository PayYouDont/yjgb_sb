package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceParamValMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceParamVal;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceParamValService;

@Service
public class DeviceParamValServiceImpl implements DeviceParamValService{
	
	@Resource
	private DeviceParamValMapper dao;

	@Override
	public DeviceParamVal selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int save(DeviceParamVal deviceParamVal) {
		return deviceParamVal.getId() == null ? dao.insertSelective(deviceParamVal)
				: dao.updateByPrimaryKeySelective(deviceParamVal);
	}
	@Override
	public List<DeviceParamVal> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
}
