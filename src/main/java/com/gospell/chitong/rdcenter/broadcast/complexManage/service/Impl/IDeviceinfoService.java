package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceinfoService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IDeviceinfoService implements DeviceinfoService{
	
	@Resource
	private DeviceinfoMapper dao;
	
	@Resource
	private AdministrativeMapper adsDao;

	@Override
	public int save(Deviceinfo deviceinfo) throws Exception {
		if(deviceinfo.getId()!=null) {
			deviceinfo.setUpdateBy(ShiroUtils.getUser().getName());
			return dao.updateByPrimaryKeySelective(deviceinfo);
		}
		deviceinfo.setCreateBy(ShiroUtils.getUser().getName());
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
	public List<Deviceinfo> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return dao.count(map);
	}

	@Override
	public List<String> findByCodes(String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		String [] codes = code.split(";");
		List<String> addressList=new ArrayList<String>();
		for(int i=0;i<codes.length;i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("code", codes[i]);
			List<Administrative> ads = adsDao.list(map);
			Administrative ad = null;
			if(ads.size()>0) {
				ad = ads.get(0);
				int level = ad.getCodeLevel();
				if(level<=3) {
					addressList.add(ad.getName());
				}else {
					//获取区级code
					String subcode = codes[i].substring(0,6)+"000000";
					map = new HashMap<>();
					map.put("code", subcode);
					List<Administrative> list = adsDao.list(map);
					if(list.size()>0) {
						Administrative pad = list.get(0);
						addressList.add(pad.getName());
					}
				}
			}
		}
		return addressList;
	}
	
}
