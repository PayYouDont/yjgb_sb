package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;

public interface DeviceInfoService extends BaseService<Deviceinfo,Integer>{
	
	List<String> findByCodes(String code);
	
	List<Devicemodel> getDeviceModelList(Map<String,Object> map);
	
	List<Deviceinfo> getListByModel(Devicemodel model);
	
	List<Deviceinfo> getRegistListByType(String devtype);

    int regist(Deviceinfo deviceinfo) throws Exception;
    int update(Deviceinfo deviceinfo) throws Exception;
}
