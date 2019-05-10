package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevModelParamRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelparamMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelParamService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class DeviceModelpParamService implements DeviceModelParamService{
	
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
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public List<DevModelParamRelation> findByMap(Map<String, Object> map) {
		return dmprdao.list(map);
	}

	@Override
	public Devicemodelparam selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}
	
	
}
