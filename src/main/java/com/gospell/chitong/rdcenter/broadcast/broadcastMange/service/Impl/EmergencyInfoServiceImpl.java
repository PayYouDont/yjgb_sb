package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.AreaCodeChineseService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaylanguageMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.InfosourceMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;
import com.gospell.chitong.rdcenter.broadcast.util.EBMessageUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService {

	@Resource
	private EmergencyinfoMapper dao;

	@Resource
	private DisplaylanguageMapper dldao;

	@Resource
	private DisplaymethodMapper dmdao;

	@Resource
	private AccidenttypeMapper atdao;
	
	@Resource 
	private AccidentlevelMapper aldao;
	
	@Resource 
	private InfosourceMapper infodao;
	
	@Resource
	private ServerProperties serverProperties;
	@Resource
	private AreaCodeChineseService accService;

	@Override
	public List<Displaylanguage> DisplaylanguageList(Map<String,Object> map){
		return dldao.list(map);
	}
	@Override
	public List<Displaymethod> DisplaymethodList(Map<String,Object> map){
		return dmdao.list(map);
	}
	@Override
	public List<Accidenttype> AccidenttypeList(Map<String,Object> map){
		return atdao.list(map);
	}
	@Override
	public List<Accidentlevel> AccidentlevelList(Map<String,Object> map){
		return aldao.list(map);
	}
	@Override
	public List<Infosource> InfosourceList(Map<String,Object> map){
		return infodao.list(map);
	}
	@Override
	public int deleteById(Integer id) throws Exception {
		int i = dao.deleteByPrimaryKey(id);
		return i;
	}

	@Override
	public int save(Emergencyinfo emer) throws Exception {
		int i = -1;
		if (emer.getId() != null) {
			emer.setCreateBy(ShiroUtils.getUser().getName());
			i = dao.updateByPrimaryKeySelective(emer);
		} else {
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
		if (flag == 0) {// 预案信息
			if (startTime == null) {
				new RuntimeException("开始时间不能为空");
			} else {
				info.setStartTime(startTime);
				long endTimeMillion = startTime.getTime() + Long.valueOf(info.getDuration()) * 60 * 1000;
				Date endTime = new Date(endTimeMillion);
				info.setEndTime(endTime);
				info.setEmergencyname(emergencyName);
				// 将id置空，存入数据库，预案保留
				info.setId(null);
				info.setStatus(5);// 待发送
				info.setFlag(1);
			}
		} else {
			// 审核的是正常的应急消息
			info.setStatus(5);// 待发送
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
		for (int i = 0; i < ids.length; i++) {
			result += dao.deleteByPrimaryKey(ids[i]);
		}
		return result;
	}

	/**
	 * 将Emergencyinfo类转成json对象(搬运的旧项目，后期优化)
	 * <p>
	 * Title: getEmerJson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param emer
	 * @return
	 * @throws Exception
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService#getEmerJson(com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo)
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月12日 上午9:58:46
	 */
	@Override
	public String getEmerJson(Emergencyinfo emer) throws Exception {
		// EmergencyInfoJson是一个通过jackjson框架，把对象转换为我们需要（自定义内容）的json
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
		EBM_Info.setEBM_class(2);// 操作类型
		if (emer.getProgramId() != null) {
			EBM_Info.setDetail_ProgramNum(emer.getProgramId());
		}
		List<String> EBM_resource_code = new ArrayList<String>();

		String v_addressCode = emer.getAddresscode();
		if (!v_addressCode.equals("") && v_addressCode != null) {
			String[] addressCodeArray = v_addressCode.split(";");
			for (int i = 0; i < addressCodeArray.length; i++) {
				//addressCodeArray[i] = "0000" + addressCodeArray[i] + "00";
				addressCodeArray[i] = "4" + addressCodeArray[i] + "0101"+"01"+"01"+"01";
			}
			EBM_resource_code = Arrays.asList(addressCodeArray);
			EBM_Info.setEBM_resource_code(EBM_resource_code);

		}

		// EBM_Content=================================================
		List<EBM_Content> EBM_ContentList = new ArrayList<EBM_Content>();
		EBM_Content EBM_Content = new EBM_Content();
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

	/**
	 * 将xml内容存入数据库
	 * <p>Title: saveXML</p> 
	 * <p>Description: </p> 
	 * @param ebmxml
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService#saveXML(com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月27日 下午4:08:54
	 */
	@Override
	public int saveXML(EBM ebmxml) throws Exception {
		System.out.println(ebmxml);
		Emergencyinfo info = new Emergencyinfo();
		info.setAreacode(ebmxml.getMsgContent_AreaCode());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		info.setStartTime(sdf.parse(ebmxml.getMsgBasicInfo_StartTime()));
		info.setEndTime(sdf.parse(ebmxml.getMsgBasicInfo_EndTime()));
		info.setContent(ebmxml.getMsgContent_MsgDesc());
		info.setEmergencyname(ebmxml.getMsgContent_MsgTitle());
		info.setEbmId(ebmxml.getEBM_EBMID());
		info.setProgramdescription(ebmxml.getAuxiliary_AuxiliaryDesc());
		String language = ebmxml.getMsgContent_LanguageCode();
		long between=(info.getStartTime().getTime()-info.getEndTime().getTime())/(1000*60);//除以1000是为了转换成秒
		info.setSound("60");
		info.setDuration(String.valueOf(Math.abs(between)));  //持续时间
		Displaylanguage dl = null;
		if (language.equals("zho")) {
			Map<String, Object> map = new HashMap<>();
			map.put("shortname", "zhong");
			List<Displaylanguage> list = DisplaylanguageList(map);
			if(list.size()>0) {
				dl = list.get(0);
			}
		}
		if (dl == null) {
			dl = new Displaylanguage();
			dl.setLanguage(language);
			dl.setShortname(language);
			dldao.insertSelective(dl);
		}
		info.setDisplaylanguageId(dl.getId());
		// 事件类型
		Accidenttype at = null;
		String eventType = ebmxml.getMsgBasicInfo_EventType();
		if(eventType!=null) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", eventType);
			List<Accidenttype> list = AccidenttypeList(map);
			if(list.size()>0) {
				at = list.get(0);
			}else {
				at = new Accidenttype();
				at.setCode(eventType);
				atdao.insertSelective(at);
			}
		}else {
			new RuntimeException("事件类型不能为空");
		}
		info.setAccidenttypeId(at.getId());
		//事件等级
		Accidentlevel level = null;
		String servrity = ebmxml.getMsgBasicInfo_Severity();
		if(servrity!=null) {
			//levelcode
			Map<String, Object> map = new HashMap<>();
			map.put("levelcode", servrity);
			List<Accidentlevel> list = AccidentlevelList(map);
			if(list.size()>0) {
				level = list.get(0);
			}else {
				level = new Accidentlevel();
				level.setLevelcode(new Integer(servrity));
				aldao.insert(level);
			}
		}else {
			new RuntimeException("事件级别不能为空");
		}
		info.setAccidentlevelId(level.getId());
		// String EventType =
		// 是否用MP3播发
		String code = ebmxml.getAuxiliary_AuxiliaryType();
		Displaymethod dm = null;
		if (code != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", code);
			List<Displaymethod> list = DisplaymethodList(map);
			if(list.size()>0) {
				dm = list.get(0);
			}else{
				dm = new Displaymethod();
				dm.setCode(code);
				dm.setMethod("MP3播发");
				dmdao.insertSelective(dm);
			}
		} else {
			dm = dmdao.selectByPrimaryKey(1);
		}
		info.setDisplaymethodId(dm.getId());
		info.setInfosourceId(1);

		info.setEmergencycode(EBMessageUtil.generateSendtime());
		info.setStatus(2);  //初始化为待审核
		info.setFlag(1);
		info.setUnitname(serverProperties.getUnitName());
		//设置事件编码（随机数）
		info.setEmergencycode(EBMessageUtil.generateSendtime());
		info.setAddresscodename(accService.getPcodeChinese(ShiroUtils.getUser().getAreaCode()));
		if (info.getId() != null) {
			info.setCreateBy(ebmxml.getMsgBasicInfo_SenderName());
		} else {
			info.setUpdateBy(ShiroUtils.getUser().getName());
		}
		if (dao.getByEmb_id(info.getEbmId()) == null){  //验证数据库是否存在相关数据
			return dao.insertSelective(info);
		}else {
			return dao.updateByEmb_idSelective(info);
		}
	}
}