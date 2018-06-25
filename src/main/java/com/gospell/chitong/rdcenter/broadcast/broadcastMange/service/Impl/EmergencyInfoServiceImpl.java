package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Auxiliary;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBM_Content;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBM_Info;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EmerJson;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService{

	@Resource
	private EmergencyinfoMapper dao;

	@Resource
	private ServerProperties serverProperties;
	
	@Override
	public int deleteById(Integer id) throws Exception {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int save(Emergencyinfo emer) throws Exception {
		int i = -1;
		if(emer.getId()!=null) {
			emer.setCreateBy(ShiroUtils.getUser().getName());
			i = dao.updateByPrimaryKeySelective(emer);
		}else {
			emer.setUpdateBy(ShiroUtils.getUser().getName());
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
	
	/**
	 * 将Emergencyinfo类转成json对象(搬运的旧项目，后期优化)
	 * <p>Title: getEmerJson</p> 
	 * <p>Description: </p> 
	 * @param emer
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService#getEmerJson(com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月12日 上午9:58:46
	 */
	@Override
	public String getEmerJson(Emergencyinfo emer) throws Exception {
		//EmergencyInfoJson是一个通过jackjson框架，把对象转换为我们需要（自定义内容）的json
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EmerJson eJson = new EmerJson();
		// EBM_Info=======================
		EBM_Info EBM_Info = new EBM_Info();
		
		
		EBM_Info.setEBM_innerIndex(emer.getId());
		EBM_Info.setEBM_type(emer.getAccidentType().getCode());
		EBM_Info.setEBM_level(emer.getAccidentLevel().getId());
		EBM_Info.setEBM_start_time(sdf.format(emer.getStartTime()));
		EBM_Info.setEBM_end_time(sdf.format(emer.getEndTime()));
		
		
		EBM_Info.setEBM_vocie(new Integer(emer.getSound()));
		EBM_Info.setEBM_class(2);//操作类型
		if(emer.getProgramId()!=null){
			EBM_Info.setDetail_ProgramNum(emer.getProgramId());	
		}
		List<String> EBM_resource_code = new ArrayList<String>();
		
		String v_addressCode = emer.getAddresscode();
		if(!v_addressCode.equals("")&&v_addressCode!=null){
			String[] addressCodeArray = v_addressCode.split(";");
			for (int i = 0; i < addressCodeArray.length; i++) {
				addressCodeArray[i]="0000"+addressCodeArray[i]+"00";
			}
			EBM_resource_code = Arrays.asList(addressCodeArray);
			EBM_Info.setEBM_resource_code(EBM_resource_code);

		}
		
		

		//EBM_Content=================================================
		List<EBM_Content> EBM_ContentList = new ArrayList<EBM_Content>();
		EBM_Content EBM_Content =new EBM_Content();
		EBM_Content.setLanguage_code(emer.getDisplayLanguage().getShortname());
		EBM_Content.setAgency_name(serverProperties.getUnitName());
		EBM_Content.setCode_character_set(0L);
		EBM_Content.setMessage_text(emer.getContent());
		
		
		List<Auxiliary> auxiliaryList = new ArrayList<Auxiliary>();
		Auxiliary auxiliary1 = new Auxiliary();
		auxiliary1.setAuxiliary_data("1辅助数据资源地址url");
		auxiliary1.setAuxiliary_data_type(0L);
		Auxiliary auxiliary2 = new Auxiliary();
		auxiliary2.setAuxiliary_data("2辅助数据资源地址url");
		auxiliary2.setAuxiliary_data_type(0L);
		auxiliaryList.add(auxiliary1);
		auxiliaryList.add(auxiliary2);
		EBM_Content.setAuxiliary(auxiliaryList);
		EBM_ContentList.add(EBM_Content);
		eJson.setEBM_Info(EBM_Info);
		eJson.setEBM_Content(EBM_ContentList);
		return JsonUtil.toJson(eJson);
	}
}
