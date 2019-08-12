package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.config.ApplicationStartupConifg;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info.EBD_EBRDTInfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicetypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicetype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService{
	
	@Resource
	private DeviceinfoMapper dao;
	
	@Resource
	private DevicemodelMapper dmdao;
	
	@Resource
	private AdministrativeMapper adsDao;
    @Value("${netty.url}")
    private String url;
    @Value("${netty.port}")
    private String port;
	
	@Override
	public int save(Deviceinfo deviceinfo) throws Exception {
		if(deviceinfo.getId()!=null) {
			return dao.updateByPrimaryKeySelective(deviceinfo);
		}
		return dao.insertSelective(deviceinfo);
	}

	@Override
	public int delete(Integer id) throws Exception {
        dao.deleteByPrimaryKey(id);
        ApplicationStartupConifg.updateDeviceMap ();
		return 1;
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
		String [] codes = code.split(",");
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月19日 上午10:37:23
	 */
	@SuppressWarnings("unlikely-arg-type")
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
	public int sendDeviceInfoCMD(Deviceinfo deviceinfo) throws Exception{
        Map<String,Object> map = new HashMap<> ();
        List<Map<String,Object>> commands = new ArrayList<> ();
        Map<String,Object> deviceCmd = new LinkedHashMap<> ();
        deviceCmd.put ("CMD_Tag",5);
        deviceCmd.put ("CMD_Name","Set_Logic_Address");
        List<Map<String,Object>> CMD_Data = new ArrayList<> ();
        Map<String,Object> dataMap = new LinkedHashMap<> ();
        dataMap.put ("Physical_address",deviceinfo.getDevdsn ());
        dataMap.put ("Logic_address",EBDcodeUtil.getEBRID (deviceinfo.getDevaddresscode ()));
        CMD_Data.add (dataMap);
        deviceCmd.put ("CMD_Data",CMD_Data);
		ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        /*Map<String,Object> rebackCmd = new LinkedHashMap<> ();
        rebackCmd.put ("CMD_Tag",7);
        rebackCmd.put ("CMD_Name","Set_Reback_Para");
        List<Map<String,Object>> rebbackCMD_Data = new ArrayList<> ();
        Map<String,Object> rebbackDataMap = new LinkedHashMap<> ();
        rebbackDataMap.put ("Reback_address",url+":"+port);
        ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        rebbackDataMap.put ("Resource_code",serverProperties.getSRC_EBRID ());
        rebbackDataMap.put ("Resource_code_type",2);
        rebbackDataMap.put ("Reback_type",2);
        rebbackCMD_Data.add (rebbackDataMap);
        rebackCmd.put ("CMD_Data",rebbackCMD_Data);*/
        commands.add (deviceCmd);
        //commands.add (rebackCmd);
        map.put ("Commands",commands);
        String json = JsonUtil.toJson (map);
		LoggerUtil.print(this.getClass(),json);
        String result = HttpClientUtil.sendPostDataByJson(serverProperties.getCmdChannel (), json,"utf-8");
        if(result.toLowerCase().equals ("ok")){
            return 200;
        }
        return 0;
    }
    @Override
    public int regist(Deviceinfo deviceinfo) throws Exception {
        int status = sendDeviceInfoCMD (deviceinfo);
        if(status==200){
            Map<String,Object> map = new HashMap<> ();
            map.put ("code",deviceinfo.getDevaddresscode ());
            Administrative administrative = adsDao.list (map).get (0);
            deviceinfo.setDevaddress (administrative.getName ());
            deviceinfo.setCreateBy (ShiroUtils.getUser ().getName ());
            deviceinfo.setOnregister ("1");
            EBD_EBRDTInfo ebrdtInfo = EBD_EBRDTInfo.createInstance (deviceinfo);
            save (deviceinfo);
			ApplicationStartupConifg.updateDeviceMap ();
            try {
				TarUtil.sendEBDToSuperior (ebrdtInfo);
			}catch (Exception e){
            	LoggerUtil.log(this.getClass(),e);
			}
            return 1;
        }
        return -1;
    }

    @Override
    public int update(Deviceinfo deviceinfo) throws Exception {
	    Integer deviceId = deviceinfo.getId ();
	    Deviceinfo info = selectById (deviceId);
        int status;
	    if(!info.getDevaddresscode ().equals (deviceinfo.getDevaddresscode ())){//更改区域码后发送信息到后台
			info.setDevaddresscode(deviceinfo.getDevaddresscode());
			info.setLng(deviceinfo.getLng());
			info.setLat(deviceinfo.getLat());
            status = sendDeviceInfoCMD (info);
            if(status==200){
                Map<String,Object> map = new HashMap<> ();
                map.put ("code",info.getDevaddresscode ());
                Administrative administrative = adsDao.list (map).get (0);
				info.setDevaddress (administrative.getName ());
				info.setUpdateBy (ShiroUtils.getUser ().getName ());
                EBD_EBRDTInfo ebrdtInfo = EBD_EBRDTInfo.createInstance (info);
                //主动上报
                TarUtil.sendEBDToSuperior (ebrdtInfo);
                return save (info);
            }else{
                return -1;
            }
        }else{
            deviceinfo.setUpdateBy (ShiroUtils.getUser ().getName ());
            return save (deviceinfo);
        }
    }
	/**
	 * @Author peiyongdong
	 * @Description ( 根据devType获取设备 )
	 * @Date 14:20 2019/5/15
	 * @Param [devType]
	 * @return java.util.List<com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo>
	 **/
	@Override
	public List<Deviceinfo> getDeviceByType(String devType) {
		DevicetypeMapper dtDao = ApplicationContextRegister.getBean (DevicetypeMapper.class);
		Map<String,Object> map = new HashMap<>();
		map.put ("devtype",devType);
		List<Devicetype> deviceTypes = dtDao.list (map);
		Devicetype devicetype = null;
		if(deviceTypes!=null&&deviceTypes.size ()>0){
			devicetype = deviceTypes.get (0);
		}
		if (devicetype==null){
			return null;
		}
		Integer deviceTypeId = devicetype.getId ();
		map = new HashMap<> ();
		map.put ("devicetypeId",deviceTypeId);
		List<Devicemodel> deviceModels = dmdao.list (map);
		List<Deviceinfo> list = new ArrayList<> ();
		for (Devicemodel deviceModel:deviceModels){
			Integer deviceModelId = deviceModel.getId ();
			Map<String,Object> objectMap = new HashMap<> ();
			objectMap.put ("devicemodelId",deviceModelId);
			List<Deviceinfo> deviceInfos = dao.list (objectMap);
			list.addAll (deviceInfos);
		}
		return list;
	}
}
