package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.DeviceJson;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AdministrativeService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.DeviceinfoService;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/backCommunicationAction")
public class BackCommunicationAction extends BaseAction{
	
	@Resource
	private EmergencyInfoService emerService;
	@Resource
	private DeviceinfoService devService;
	@Resource
	private AdministrativeService adService;
	
	/**
	 * 播发应急信息
	 * @Title: sendEmer 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param emerId
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月12日 下午4:00:59
	 */
	@RequestMapping("/sendEmer")
	public String sendEmer(Integer emerId){
		Emergencyinfo emer = emerService.selectById(emerId);
		String url = serverProperties.getEmerSendIpAddress();
		String sendPost = "";
		try {
			String json = emerService.getEmerJson(emer);
			sendPost = HttpClientUtil.sendPostDataByJson(url,json,"utf8");
			System.out.println(sendPost);
			if(!sendPost.equals("")){
				JSONObject jsonResult = JSONObject.fromObject(sendPost); 
				if(jsonResult.getString("Result").equals("success")){
					emer.setEbmId(jsonResult.getString("EBM_ID"));
					// 停止时 判断是否为预案 
					if(0==emer.getFlag()){
						emer.setStatus(2);
					}
					emer.setStatus(6);//已发送
					emerService.save(emer);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return sendPost;
	}
	/**
	 * 获取输入资源
	 * @Title: getProgrameJson 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月12日 下午4:00:31
	 */
	@PostMapping("/getProgrameJson")
	public HashMap<String,Object> getProgrameJson(){
		String url = serverProperties.getProgramAddress();
		try {
			String result = HttpClientUtil.sendPostDataByJson(url,"","utf8");
			return JsonWrapper.successWrapper(result);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		} 
		
	}
	/**
	 * 注册设备
	 * @Title: baseSave 
	 * @Description: TODO(注册设备，参考旧项目，后期优化) 
	 * @param @param deviceInfo
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月12日 上午11:28:50
	 */
	@PostMapping("/baseSave")
	public HashMap<String,Object> baseSave(Deviceinfo deviceInfo){
		String devHexcode = "";//设备地址号  ==设备寻址号截取后4个字
		String devCode = "";//设备寻址号==资源类型（2字）+资源子类型（2字）+区域行政编码（12字）+设备编号（2字）  共18字
		Map<String,Object> map = new HashMap<>();
		map.put("devDsn", deviceInfo.getDevdsn());
		Deviceinfo info = devService.list(map).size()>0?devService.list(map).get(0):new Deviceinfo();
		//查询此区域下设备
		map = new HashMap<>();
		map.put("Devaddresscode", deviceInfo.getDevaddresscode());
		List<Deviceinfo> Deviceinfos = devService.list(map);
		List<Long> nums = new ArrayList<Long>();//初始化数组
	        if(Deviceinfos.size()==0){nums.add(0L);}
	        else{
	        	for (Deviceinfo dev : Deviceinfos) {
	        		String devhexcode = dev.getDevhexcode();
	        		if(devhexcode!=null) {
						nums.add(Long.valueOf(devhexcode));
	        		}
				}	
	        }
			Long Max = Collections.max(nums);//设置最大值Max
			String devnum="";
			if(Max<=8){devnum="0"+String.valueOf(Max+1);}
			else{devnum=String.valueOf(Max+1);}
			
			String myareacode="";
			if(deviceInfo.getDevaddresscode().length()==12){
				myareacode=deviceInfo.getDevaddresscode();
			}else{
				myareacode=deviceInfo.getDevaddresscode()+"000000";
			}
			devCode="0000"+myareacode+devnum;
			devHexcode=devCode.substring(devCode.length()-2);//截取后4位
			
			//设置parentPath字段值,方便通过区域码 范围 查找
			String currentCode = deviceInfo.getDevaddresscode();
			map = new HashMap<>();
			map.put("code",currentCode);
			Administrative admin = adService.list(map).size()>0?adService.list(map).get(0):new Administrative();
			
			deviceInfo.setParentpath(admin.getParentPath());

			//设置一些参数到设备信息对象中
			deviceInfo.setId(info.getId());
			deviceInfo.setDevhexcode(devHexcode);//设备地址号
			deviceInfo.setDevcode(devCode);//设备寻址号
			//参数
			DeviceJson  deviceJson= new DeviceJson();
			deviceJson.setDevDsn(deviceInfo.getDevdsn());
			deviceJson.setDevHexcode(deviceInfo.getDevhexcode());
			deviceJson.setDevCode(deviceInfo.getDevcode());
			String json = JsonUtil.toJson(deviceJson);
			String url = serverProperties.getSetParasAddress();
			try {
				String sendPost = HttpClientUtil.sendPostDataByJson(url, json, "utf8");
				if(sendPost.equals("OK")){
					info.setUpdateBy(getUserName());
					info.setDevaddress(deviceInfo.getDevaddress());
					info.setDevaddresscode(deviceInfo.getDevaddresscode());
					info.setDevname(deviceInfo.getDevname());
					info.setLat(deviceInfo.getLat());
					info.setLng(deviceInfo.getLng());
					info.setDeviceModel(deviceInfo.getDeviceModel());
					devService.save(info);
				}
				return JsonWrapper.successWrapper();
			} catch (Exception e) {
				e.printStackTrace();
				return JsonWrapper.failureWrapper();
			}
	}
	/**
	 * 停止播发应急信息
	 * @Title: emerStopMessage 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param emerId
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月12日 下午4:01:36
	 */
	@PostMapping("/emerStopMessage") 
	public HashMap<String,Object> emerStopMessage(Integer emerId){
		Emergencyinfo info = emerService.selectById(emerId);
		String ebm_ID = info.getEbmId();
		if(ebm_ID!=""&&!ebm_ID.equals("")){
			String url = serverProperties.getEmerStopAddress();
			try {
				String sendGet = HttpClientUtil.sendGetData(url,"EBM_ID="+ebm_ID);
				if(sendGet=="OK"||sendGet.equals("OK")){
					info.setStatus(11);//播发结束
					emerService.save(info);
					return JsonWrapper.successWrapper();
				}
				return JsonWrapper.failureWrapper("停止播发失败");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return JsonWrapper.failureWrapper(e.getMessage());
			}
		}
		return JsonWrapper.failureWrapper("ebm_ID为空");
	}
}