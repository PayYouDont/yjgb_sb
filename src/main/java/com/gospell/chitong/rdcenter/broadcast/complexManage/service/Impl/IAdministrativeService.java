package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AdministrativeService;

import net.sf.json.JSONArray;

@Service
public class IAdministrativeService implements AdministrativeService{
	
	@Resource
	private AdministrativeMapper dao;
	
	@Override
	public Administrative findByCode(String code) {
		Map<String,Object> map = new HashMap<>();
		map.put("code",code);
		return dao.list(map)==null?null:dao.list(map).get(0);
	}

	@Override
	public List<Administrative> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public String getTreeStr(String areaCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", areaCode);
		List<Administrative> list = dao.list(map);
		Administrative ative = null;
		if(list.size()>0) {
			ative = list.get(0);
		}else {
			return null;
		}
		ative = getChildList(ative);
		ative.setState("closed");
		String jsonstr=JSONArray.fromObject(ative).toString().replaceAll("name", "text").replaceAll("id", "areaId").replaceAll("code", "id");
		return jsonstr;
	}
	public Administrative getChildList(Administrative ad){
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("parentCode", ad.getCode());
			map.put("sort", "id");
			map.put("order", "ASC");
			List<Administrative> childList = dao.list(map);
			if(childList != null && childList.size() > 0){
				ad.setChildren(childList);
				for (Administrative administrative : childList) {
					if(administrative.getCodeLevel()<5) {
						administrative.setState("closed");
					}
					getChildList(administrative);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ad;
	}
}
