package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;

@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService{

	@Resource
	private EmergencyinfoMapper dao;

	@Override
	public int deleteById(Integer id) throws Exception {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int save(Emergencyinfo emer) throws Exception {
		int i = -1;
		if(emer.getId()!=null) {
			i = dao.updateByPrimaryKeySelective(emer);
		}else {
			i = dao.insert(emer);
		}
		return i;
	}

	@Override
	public Emergencyinfo selectById(Integer id) {
		Emergencyinfo emer = dao.selectByPrimaryKey(id);
		return emer;
	}

	@Override
	public List<Emergencyinfo> queryPage(Map<String, Object> map) {
		List<Emergencyinfo> list = dao.list(map);
		return list;
	}

	@Override
	public int countPage(Map<String, Object> map) {
		int i = dao.count(map);
		return i;
	}

	
	public String review(Emergencyinfo info) {
		Integer id = info.getId();
		String emergencyName = info.getEmergencyname();
		Date startTime = info.getStartTime();
		info = selectById(id);
		Integer flag = info.getFlag();
		if(flag==0) {//预案信息
			if(startTime==null) {
				new RuntimeException("开始时间不能为空");
			}else {
				info.setStartTime(startTime);
				long endTimeMillion = startTime.getTime() + Long.valueOf(info.getDuration())*60*1000;
				Date endTime = new Date(endTimeMillion);
				info.setEndTime(endTime);
				info.setEmergencyname(emergencyName);
				// 将id置空，存入数据库，预案保留
				info.setId(null);
				info.setStatus(5);//待发送
				info.setFlag(1);
			}
		}else {
			// 审核的是正常的应急消息
			info.setStatus(5);//待发送
		}
		try {
			save(info);
			return "审核成功";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public int deleteByIds(Integer[] ids) throws Exception {
		int result = 0;
		for(int i=0;i<ids.length;i++) {
			result += dao.deleteByPrimaryKey(ids[i]);
		}
		return result;
	}
	
}
