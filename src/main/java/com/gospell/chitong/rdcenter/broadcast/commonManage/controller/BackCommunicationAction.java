package com.gospell.chitong.rdcenter.broadcast.commonManage.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.DeviceJson;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceParamVal;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodelparam;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceModelService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceParamValService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/backCommunicationAction")
public class BackCommunicationAction extends BaseAction {

	@Resource
	private EmergencyInfoService emerService;
	@Resource
	private DeviceInfoService devService;
	@Resource
	private AdministrativeService adService;
	@Resource
	private DeviceModelService dmService;
	@Resource
	private DeviceParamValService dpvService;

	/**
	 * 播发应急信息
	 * 
	 * @Title: sendEmer
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            emerId
	 * @param @return
	 *            设定文件
	 * @return String 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月12日 下午4:00:59
	 */
	@Log("发送应急信息")
	@RequestMapping("/sendEmer")
	public String sendEmer(Integer emerId) {
		Emergencyinfo emer = emerService.selectById(emerId);
		String url = serverProperties.getEmerSendIpAddress();
		String sendPost = "";
		try {
			String json = emerService.getEmerJson(emer);
			sendPost = HttpClientUtil.sendPostDataByJson(url, json, "utf8");
			if (!sendPost.equals("")) {
				JSONObject jsonResult = JSONObject.fromObject(sendPost);
				if (jsonResult.getString("Result").equals("success")) {
					emer.setEbmId(jsonResult.getString("EBM_ID"));
					// 停止时 判断是否为预案
					if (0 == emer.getFlag()) {
						emer.setStatus(2);
					}
					emer.setStatus(6);// 已发送
					emerService.save(emer);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sendPost;
	}

	/**
	 * 获取输入资源
	 * 
	 * @Title: getProgrameJson
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 *            设定文件
	 * @return HashMap<String,Object> 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月12日 下午4:00:31
	 */
	@PostMapping("/getProgrameJson")
	public HashMap<String, Object> getProgrameJson() {
		String url = serverProperties.getProgramAddress();
		try {
			String result = HttpClientUtil.sendPostDataByJson(url, "", "utf8");
			return JsonWrapper.successWrapper(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonWrapper.failureWrapper();
		}

	}

	/**
	 * 注册设备
	 * 
	 * @Title: baseSave
	 * @Description: TODO(注册设备，参考旧项目，后期优化)
	 * @param @param
	 *            deviceInfo
	 * @param @return
	 *            设定文件
	 * @return HashMap<String,Object> 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月12日 上午11:28:50
	 */
	@Log("注册设备")
	@PostMapping("/baseSave")
	@Transactional
	public HashMap<String, Object> baseSave(Deviceinfo deviceInfo) {
		String devHexcode = "";// 设备地址号 ==设备寻址号截取后4个字
		String devCode = "";// 设备寻址号==资源类型（2字）+资源子类型（2字）+区域行政编码（12字）+设备编号（2字） 共18字
		Map<String, Object> map = new HashMap<>();
		map.put("devDsn", deviceInfo.getDevdsn());
		Deviceinfo info = devService.list(map).size() > 0 ? devService.list(map).get(0) : new Deviceinfo();
		// 查询此区域下设备
		map = new HashMap<>();
		map.put("Devaddresscode", deviceInfo.getDevaddresscode());
		List<Deviceinfo> Deviceinfos = devService.list(map);
		List<Long> nums = new ArrayList<Long>();// 初始化数组
		if (Deviceinfos.size() == 0) {
			nums.add(0L);
		} else {
			for (Deviceinfo dev : Deviceinfos) {
				String devhexcode = dev.getDevhexcode();
				if (devhexcode != null) {
					nums.add(Long.valueOf(devhexcode));
				}
			}
		}
		Long Max = Collections.max(nums);// 设置最大值Max
		String devnum = "";
		if (Max <= 8) {
			devnum = "0" + String.valueOf(Max + 1);
		} else {
			devnum = String.valueOf(Max + 1);
		}

		String myareacode = "";
		if (deviceInfo.getDevaddresscode().length() == 12) {
			myareacode = deviceInfo.getDevaddresscode();
		} else {
			myareacode = deviceInfo.getDevaddresscode() + "000000";
		}
		devCode = "0000" + myareacode + devnum;
		devHexcode = devCode.substring(devCode.length() - 2);// 截取后4位

		// 设置parentPath字段值,方便通过区域码 范围 查找
		String currentCode = deviceInfo.getDevaddresscode();
		map = new HashMap<>();
		map.put("code", currentCode);
		Administrative admin = adService.list(map).size() > 0 ? adService.list(map).get(0) : new Administrative();

		deviceInfo.setParentpath(admin.getParentPath());

		// 设置一些参数到设备信息对象中
		deviceInfo.setId(info.getId());
		deviceInfo.setDevhexcode(devHexcode);// 设备地址号
		deviceInfo.setDevcode(devCode);// 设备寻址号
		// 参数
		DeviceJson deviceJson = new DeviceJson();
		deviceJson.setDevDsn(deviceInfo.getDevdsn());
		deviceJson.setDevHexcode(deviceInfo.getDevhexcode());
		deviceJson.setDevCode(deviceInfo.getDevcode());
		String json = JsonUtil.toJson(deviceJson);
		String url = serverProperties.getSetParasAddress();
		try {
			String sendPost = HttpClientUtil.sendPostDataByJson(url, json, "utf8");
			if (sendPost.equals("OK")) {
				deviceInfo.setStatus("00000001");// 注册
				deviceInfo.setCreateBy(getUserName());
				devService.save(info);
				//初始化的时候把设备型号下的参数设置得到参数值表中（只是这时候值为null）
				Devicemodel deviceModel = dmService.selectById(deviceInfo.getDevicemodelId());
				List<Devicemodelparam> dmps = dmService.getDevParmByDevicemodel(deviceModel);
				for (Devicemodelparam deviceModelParam : dmps) {
					DeviceParamVal val = new DeviceParamVal();
					val.setDeviceInfo(deviceInfo);
		    		val.setParamFormCheck(deviceModelParam.getParamFormCheck());
		    		val.setParamName(deviceModelParam.getParamName());
		    		dpvService.save(val);
				}
				//注冊后创建对应的tar包
				//EBRDTInfo.createTar(deviceInfo);
			}
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error("注册设备失败",e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}

	/**
	 * 停止播发应急信息
	 * 
	 * @Title: emerStopMessage
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param
	 *            emerId
	 * @param @return
	 *            设定文件
	 * @return HashMap<String,Object> 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年6月12日 下午4:01:36
	 */
	@Log("停止发送应急信息")
	@PostMapping("/emerStopMessage")
	@Transactional
	public HashMap<String, Object> emerStopMessage(Integer emerId) {
		Emergencyinfo info = emerService.selectById(emerId);
		String ebm_ID = info.getEbmId();
		if (ebm_ID != "" && !ebm_ID.equals("")) {
			String url = serverProperties.getEmerStopAddress();
			try {
				String sendGet = HttpClientUtil.sendGetData(url, "EBM_ID=" + ebm_ID);
				if (sendGet == "OK" || sendGet.equals("OK")) {
					info.setStatus(11);// 播发结束
					emerService.save(info);
					return JsonWrapper.successWrapper();
				}
				return JsonWrapper.failureWrapper("停止播发失败");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return JsonWrapper.failureWrapper(e.getMessage());
			}
		}
		return JsonWrapper.failureWrapper("ebm_ID为空");
	}

	@RequestMapping("/getRegisterDevice")
	@ResponseBody
	public HashMap<String, Object> getRegisterDevice(String registerDeviceJson) {
		System.out.println(registerDeviceJson);
		return null;
	}
	@Log("设备参数设置")
	@RequestMapping("/devParamSetting")
	@ResponseBody
	@Transactional
	public HashMap<String, Object> devParamSetting(HttpServletRequest request) {
		String id = request.getParameter("id");
		Deviceinfo info = devService.selectById(Integer.valueOf(id));
		Map<String,Object> map = new HashMap<>();
		map.put("deviceInfoId",info.getId());
		List<DeviceParamVal> devModelParamList = dpvService.list(map);
		//参数
		Map<String, Object> paramMap = new HashMap<String, Object>();		
		for (DeviceParamVal deviceParamVal : devModelParamList) {
			String parameter = request.getParameter(deviceParamVal.getParamVariable());
			if(deviceParamVal.getVal()==null){
				paramMap.put(deviceParamVal.getParamVariable(), parameter);
			}else if(!deviceParamVal.getVal().equals(parameter)){
				paramMap.put(deviceParamVal.getParamVariable(), parameter);
			}
		}
		DeviceJson  deviceJson= new DeviceJson();
		deviceJson.setDevDsn(info.getDevdsn());
		deviceJson.setDevHexcode(info.getDevhexcode());
		deviceJson.setDevCode(info.getDevcode());
		deviceJson.setParamMap(paramMap);
		String json = JsonUtil.toJson(deviceJson);
		String url = serverProperties.getSetParasAddress();
		try {
			String sendPost = HttpClientUtil.sendPostDataByJson(url, json,"utf8");
			if(sendPost.equals("OK")){
				for (DeviceParamVal deviceParamVal : devModelParamList) {
					String parameter = request.getParameter(deviceParamVal.getParamVariable());
					if(parameter!=null){
						deviceParamVal.setVal(parameter);
						dpvService.save(deviceParamVal);
					}
				}
			}
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
