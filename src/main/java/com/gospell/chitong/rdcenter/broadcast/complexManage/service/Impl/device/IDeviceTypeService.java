package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceTypeService;

@Service
public class IDeviceTypeService implements DeviceTypeService{
	
	@Resource
	private DevicetypeMapper dao;
	@Resource
	private DevicemodelMapper dmdao;

	@Override
	public int delete(Integer id) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("devicetypeId", id);
		List<Devicemodel> list = dmdao.list(map);
		if(list.size()>0) {
			return -1;
		}
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Devicetype selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int save(Devicetype entity) throws Exception{
		if(entity.getId()!=null) {
			return dao.updateByPrimaryKeySelective(entity);
		}
		return dao.insertSelective(entity);
	}

	@Override
	public List<Devicetype> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
	
}
