package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

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
	public String getTreeStr(String areaCode) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", areaCode);
		List<Administrative> list = list(map);
		String jsonstr=JsonUtil.toJson(list).replaceAll("name", "text").replaceAll("id", "areaId").replaceAll("code", "id");
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

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月26日 上午10:20:18
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月26日 上午11:35:39
	 */
	@Override
	public List<Administrative> list(Map<String, Object> map) {
		List<Administrative> list = dao.list(map);
		if(list.size()==0) {
			return null;
		}
		for (Administrative ative : list) {
			if(ative.getCodeLevel()<5) {
				ative = getChildList(ative);
				ative.setState("closed");
			}
		}
		return list;
	}
}
