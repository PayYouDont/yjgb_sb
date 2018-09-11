package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.StatisticsService;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;


@Service
public class IStatisticsService implements StatisticsService{

	@Resource
	private EmergencyinfoMapper emerDao;
	
	@Override
	public List<Map<String, Object>> getStateData() throws Exception{
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Integer> firstLevelList = new ArrayList<Integer>();
		List<Integer> secondLevelList = new ArrayList<Integer>();
		List<Integer> thirdLevelList = new ArrayList<Integer>();
		List<Integer> otherLevelList = new ArrayList<Integer>();
		for (int i = 0; i < 12; i++) {
			String firstDateStr = DateUtils.getFirstDayOfMonth(i)+ " 00:00:00";
			String lastDateStr = DateUtils.getLastDayOfMonth(i)+" 23:59:59";
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date firstDate = sdf.parse(firstDateStr);
			Date lastDate = sdf.parse(lastDateStr);
			//List<Emergencyinfo> emergencyInfoList = emergencyInfoService.getEntities(" start_time between :firstDate and :lastDate",firstDate,lastDate);
			Map<String,Object> map = new HashMap<>();
			map.put("firstDate", firstDate);
			map.put("lastDate", lastDate);
			List<Emergencyinfo> emergencyInfoList = emerDao.getByStartTime(map);
			Integer firstLevelCount = 0;
			Integer secondLevelCount = 0;
			Integer thirdLevelCount = 0;
			Integer otherLevelCount = 0;

			for (Emergencyinfo emergencyInfo : emergencyInfoList) {
				if(emergencyInfo.getAccidentLevel().getLevelcode()==1){
					firstLevelCount++;
				}else if(emergencyInfo.getAccidentLevel().getLevelcode()==2){
					secondLevelCount++;
				}else if(emergencyInfo.getAccidentLevel().getLevelcode()==3){
					thirdLevelCount++;
				}else{
					otherLevelCount++;
				}
			}
			firstLevelList.add(firstLevelCount);
			secondLevelList.add(secondLevelCount);
			thirdLevelList.add(thirdLevelCount);
			otherLevelList.add(otherLevelCount);
		}

		Map<String,Object> firstLevelMap = new HashMap<String,Object>();
		firstLevelMap.put("name","1级(特别重大)");
		firstLevelMap.put("type", "bar");
		firstLevelMap.put("data", firstLevelList);
		Map<String, Object> secondLevelMap = new HashMap<String, Object>();
		secondLevelMap.put("name", "2级(重大)");
		secondLevelMap.put("type", "bar");
		secondLevelMap.put("data", secondLevelList);
		Map<String,Object> thirdLevelMap = new HashMap<String, Object>();
		thirdLevelMap.put("name", "3级(较大)");
		thirdLevelMap.put("type", "bar");
		thirdLevelMap.put("data", thirdLevelList);
		Map<String, Object> otherLevelMap = new HashMap<String,Object>();
		otherLevelMap.put("name", "4级(一般)");
		otherLevelMap.put("type", "bar");
		otherLevelMap.put("data", otherLevelList);

		result.add(firstLevelMap);
		result.add(secondLevelMap);
		result.add(thirdLevelMap);
		result.add(otherLevelMap);
		return result;
	}

	@Override
	public List<Map<String, Object>> getStatusData() throws Exception {
		List<Emergencyinfo> emergencyInfoList = emerDao.list(new HashMap<>());
		Integer needPost = 0;
		Integer needExamine = 0;
		Integer notPassExamine = 0;
		Integer examined = 0;
		Integer needBroadcast = 0;
		Integer broadcasted = 0;
		Integer broadcastedOk = 0;
		Integer waitBroadcast = 0;
		Integer broadcasting = 0;
		Integer broadcastedErr = 0;
		Integer broadcastedEnd = 0;
		for (Emergencyinfo emergencyInfo : emergencyInfoList) {
			if(1==emergencyInfo.getStatus()){
				needPost++;
			}else if(2==emergencyInfo.getStatus()){
				needExamine++;
			}else if(3==emergencyInfo.getStatus()){
				notPassExamine++;
			}else if(4==emergencyInfo.getStatus()){
				examined++;
			}else if(5==emergencyInfo.getStatus()){
				needBroadcast++;
			}else if(6==emergencyInfo.getStatus()){
				broadcasted++;
			}else if(7==emergencyInfo.getStatus()){
				broadcastedOk++;
			}else if(8==emergencyInfo.getStatus()){
				waitBroadcast++;
			}else if(9==emergencyInfo.getStatus()){
				broadcasting++;
			}else if(10==emergencyInfo.getStatus()){
				broadcastedErr++;
			}else if(11==emergencyInfo.getStatus()){
				broadcastedEnd++;
			}
		}
		List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> needPostMap = new HashMap<String, Object>();
		needPostMap.put("name", "待提交");
		needPostMap.put("value", needPost);
		result.add(needPostMap);
		Map<String, Object> needExamineMap = new HashMap<String, Object>();
		needExamineMap.put("name", "待审核");
		needExamineMap.put("value", needExamine);
		result.add(needExamineMap);
		Map<String, Object> notPassExamineMap = new HashMap<String, Object>();
		notPassExamineMap.put("name", "未通过审核");
		notPassExamineMap.put("value", notPassExamine);
		result.add(notPassExamineMap);
		Map<String, Object> examinedMap = new HashMap<String, Object>();
		examinedMap.put("name", "已审核");
		examinedMap.put("value", examined);
		result.add(examinedMap);
		Map<String, Object> needBroadcastMap = new HashMap<String, Object>();
		needBroadcastMap.put("name", "待发送");
		needBroadcastMap.put("value", needBroadcast);
		result.add(needBroadcastMap);
		Map<String, Object> broadcastedMap = new HashMap<String, Object>();
		broadcastedMap.put("name", "已发送");
		broadcastedMap.put("value", broadcasted);
		result.add(broadcastedMap);
		Map<String, Object> broadcastedOkMap = new HashMap<String, Object>();
		broadcastedOkMap.put("name", "发送成功");
		broadcastedOkMap.put("value", broadcastedOk);
		result.add(broadcastedOkMap);
		Map<String, Object> waitBroadcastMap = new HashMap<String, Object>();
		waitBroadcastMap.put("name", "等待播发");
		waitBroadcastMap.put("value", waitBroadcast);
		result.add(waitBroadcastMap);
		Map<String, Object> broadcastingMap = new HashMap<String, Object>();
		broadcastingMap.put("name", "正在播发");
		broadcastingMap.put("value", broadcasting);
		result.add(broadcastingMap);
		Map<String, Object> broadcastedErrMap = new HashMap<String, Object>();
		broadcastedErrMap.put("name", "播发失败");
		broadcastedErrMap.put("value", broadcastedErr);
		Map<String, Object> broadcastedEndMap = new HashMap<String, Object>();
		broadcastedEndMap.put("name", "播发结束");
		broadcastedEndMap.put("value", broadcastedEnd);
		result.add(broadcastedEndMap);
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> getTypeData(String option) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if(option == null || "".equals(option)){
			option = "all";
		}
		List<Emergencyinfo> emergencyInfoList = new ArrayList<Emergencyinfo>();
		if(option.equals("all")){
			emergencyInfoList = emerDao.list(new HashMap<>());
		}else if(option.equals("year")){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_YEAR , 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(calendar.SECOND, 0);

			Date startTime = calendar.getTime();

			calendar.add(Calendar.YEAR, 1);
			calendar.set(Calendar.DAY_OF_YEAR, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);

			Date endTime = calendar.getTime();
			//emergencyInfoList = emergencyInfoService.getEntities(" start_time between :startTime and :endTime", startTime, endTime);
			Map<String,Object> map = new HashMap<>();
			map.put("firstDate", startTime);
			map.put("lastDate", endTime);
			emergencyInfoList = emerDao.getByStartTime(map);
		}else if(option.equals("month")){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);

			Date startTime = calendar.getTime();

			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 0);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);

			Date endTime = calendar.getTime();
			Map<String,Object> map = new HashMap<>();
			map.put("firstDate", startTime);
			map.put("lastDate", endTime);
			emergencyInfoList = emerDao.getByStartTime(map);
		}else if(option.equals("week")){

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_WEEK, 1);
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date startTime = calendar.getTime();

			calendar.add(Calendar.WEEK_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.add(Calendar.DATE, -1);
			Date endTime = calendar.getTime();
			Map<String,Object> map = new HashMap<>();
			map.put("firstDate", startTime);
			map.put("lastDate", endTime);
			emergencyInfoList = emerDao.getByStartTime(map);
		}


		Map<String, Integer> nameCountMap = new HashMap<String, Integer>();
		for (Emergencyinfo emergencyInfo : emergencyInfoList) {
			Integer value = nameCountMap.get(emergencyInfo.getAccidentType().getName());
			nameCountMap.put(emergencyInfo.getAccidentType().getName(), nameCountMap.get(emergencyInfo.getAccidentType().getName()) == null ? 1 : ++value);
		}

		List<String> legendList = new ArrayList<String>();
		for(Map.Entry<String, Integer> entry : nameCountMap.entrySet()){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", entry.getKey());
			map.put("value", entry.getValue());
			result.add(map);
			legendList.add(entry.getKey());
		}

		Map<String, Object> resultMapper = new HashMap<String, Object>();
		resultMapper.put("legendData", legendList);
		resultMapper.put("seriesData", result);
		return resultMapper;
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.StatisticsService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月4日 上午11:40:38
	 */
	@Override
	public List<Emergencyinfo> list(Map<String, Object> map) {
		return emerDao.list(map);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.StatisticsService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月4日 上午11:40:38
	 */
	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return emerDao.count(map);
	}
}
