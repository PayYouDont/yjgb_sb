package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevModelParamRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicemodelparamMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.DevModelParamRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DevicemodelService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
@Service
public class IDevicemodelService implements DevicemodelService{
	@Resource
	private DevicemodelMapper dao;
	@Resource
	private DevicemodelparamMapper dmpdao;
	@Resource
	private DevicetypeMapper dpdao;
	@Resource
	private DevModelParamRelationMapper dmprdao;
	@Resource
	private DeviceinfoMapper dinfodao;
	
	@Override
	public List<Devicemodel> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public Devicemodel selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int save(Devicemodel entity) throws Exception{
		if(entity.getId()==null) {
			entity.setCreateBy(ShiroUtils.getUser().getName());
			return dao.insertSelective(entity);
		}else {
			entity.setUpdateBy(ShiroUtils.getUser().getName());
			return dao.updateByPrimaryKeySelective(entity);
		}
	}

	@Override
	public int delete(Integer id) throws Exception{
		Map<String,Object> map = new HashMap<>();
		map.put("devicemodelId", id);
		List<Deviceinfo> list = dinfodao.list(map);
		if(list.size()>0) {//型号正在被其他设备使用
			return -1;
		}
		return dao.deleteByPrimaryKey(id);
	}
	@Override
	public List<Devicemodelparam> deviceModelParamList(Map<String, Object> map) {
		return dmpdao.list(map);
	}
	@Override
	public List<Devicetype> deviceTypeList(Map<String, Object> map) {
		return dpdao.list(map);
	}

	@Override
	public List<DevModelParamRelation> devModelParamRelationList(Map<String, Object> map) {
		return dmprdao.list(map);
	}
	@Override
	public int deleteDMPRByDevModelId(Integer devModelId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("dmId", devModelId);
		List<DevModelParamRelation> list = devModelParamRelationList(map);
		int i = 0;
		for (DevModelParamRelation devModelParamRelation : list) {
			i += dmprdao.deleteByPrimaryKey(devModelParamRelation.getId());
		}
		return i;
	}

	@Override
	public int saveDMPR(Integer modelId,String paramIds) throws Exception {
		String[] ids = paramIds.split(",");
		int index = 0;
		for (int i = 0; i < ids.length; i++) {
			DevModelParamRelation dmpr = new DevModelParamRelation();
			dmpr.setDmId(modelId);
			dmpr.setDmpId(new Integer(ids[i]));
			index += dmprdao.insertSelective(dmpr);
		}
		return index;
	}
}
