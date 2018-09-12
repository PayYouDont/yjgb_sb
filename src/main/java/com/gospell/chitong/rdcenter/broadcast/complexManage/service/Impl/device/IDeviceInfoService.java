package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IDeviceInfoService implements DeviceInfoService{
	
	@Resource
	private DeviceinfoMapper dao;
	
	@Resource
	private DevicemodelMapper dmdao;
	
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
	public Deviceinfo selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Deviceinfo> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
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

	/** 
	 * <p>Title: getDeviceModelList</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService#getDeviceModelList(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月12日 上午9:16:58
	 */
	@Override
	public List<Devicemodel> getDeviceModelList(Map<String, Object> map) {
		return dmdao.list(map);
	}

	/** 
	 * <p>Title: getListByModel</p> 
	 * <p>Description: </p> 
	 * @param model
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService#getListByModel(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月19日 上午9:49:17
	 */
	@Override
	public List<Deviceinfo> getListByModel(Devicemodel model) {
		Map<String,Object> map = new HashMap<>();
		map.put("devicemodelId", model.getId());
		return list(map);
	}

	/** 
	 * <p>Title: getRegistListByType</p> 
	 * <p>Description: </p> 
	 * @param devtype
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService#getListByModelType(java.lang.String) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月19日 上午10:37:23
	 */
	@Override
	public List<Deviceinfo> getRegistListByType(String devtype) {
		List<Deviceinfo> deviceinfos = dao.list(null);
		List<Deviceinfo> list = new ArrayList<>();
		for (Deviceinfo info : deviceinfos) {
			if(info.getOnregister().equals("1")) {//已经注册
				if(info.getDeviceModel().getDeviceType().getDevtype().indexOf(devtype)!=-1) {//属于适配器还是终端
					list.add(info);
				}
			}
		}
		return list;
	}
}
