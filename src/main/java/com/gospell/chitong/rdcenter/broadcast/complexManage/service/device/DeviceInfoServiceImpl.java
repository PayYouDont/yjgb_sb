package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import java.util.*;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info.EBD_EBRDTInfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdTypeService;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DevicemodelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AdministrativeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import org.springframework.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

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
        Map<String,Object> CMD_Data = new LinkedHashMap<> ();
        CMD_Data.put ("Physical_address",deviceinfo.getDevdsn ());
        CMD_Data.put ("Physical_address",EBDcodeUtil.getEBRID (deviceinfo.getDevaddresscode ()));
        deviceCmd.put ("CMD_Data",CMD_Data);
        Map<String,Object> rebackCmd = new LinkedHashMap<> ();
        rebackCmd.put ("CMD_Tag",7);
        rebackCmd.put ("CMD_Name","Set_Reback_Para");
        Map<String,Object> rebbackCMD_Data = new LinkedHashMap<> ();
        rebbackCMD_Data.put ("Reback_address",url+":"+port);
        ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        rebbackCMD_Data.put ("Resource_code",serverProperties.getSRC_EBRID ());
        rebbackCMD_Data.put ("Resource_code_type",2);
        rebbackCMD_Data.put ("Reback_type",2);
        rebackCmd.put ("CMD_Data",rebbackCMD_Data);
        commands.add (deviceCmd);
        commands.add (rebackCmd);
        map.put ("Commands",commands);
        String json = JsonUtil.toJson (map);
        String result = HttpClientUtil.sendPostDataByJson(serverProperties.getCmdChannel (), json,"utf-8");
        if(result.equals ("ok")){
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
            TarUtil.sendEBD (ebrdtInfo);
            return save (deviceinfo);
        }
        return -1;
    }

    @Override
    public int update(Deviceinfo deviceinfo) throws Exception {
	    Integer deviceId = deviceinfo.getId ();
	    Deviceinfo info = selectById (deviceId);
        int status;
	    if(!info.getDevaddresscode ().equals (deviceinfo.getDevaddresscode ())){//更改区域码后发送信息到后台
            status = sendDeviceInfoCMD (deviceinfo);
            if(status==200){
                Map<String,Object> map = new HashMap<> ();
                map.put ("code",deviceinfo.getDevaddresscode ());
                Administrative administrative = adsDao.list (map).get (0);
                deviceinfo.setDevaddress (administrative.getName ());
                deviceinfo.setUpdateBy (ShiroUtils.getUser ().getName ());
                EBD_EBRDTInfo ebrdtInfo = EBD_EBRDTInfo.createInstance (deviceinfo);
                //主动上报
                TarUtil.sendEBD (ebrdtInfo);
                return save (deviceinfo);
            }else{
                return -1;
            }
        }else{
            deviceinfo.setUpdateBy (ShiroUtils.getUser ().getName ());
            return save (deviceinfo);
        }
    }
}
