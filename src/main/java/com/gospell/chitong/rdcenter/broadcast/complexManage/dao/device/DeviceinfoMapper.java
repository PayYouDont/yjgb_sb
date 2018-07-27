package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;

public interface DeviceinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deviceinfo record);

    int insertSelective(Deviceinfo record);

    Deviceinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deviceinfo record);

    int updateByPrimaryKey(Deviceinfo record);
    
    List<Deviceinfo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
}