package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.AreaCodeChineseService;
import com.gospell.chitong.rdcenter.broadcast.util.EBMessageUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@Controller
@RequestMapping("/emergencyInfoAction")
public class EmergencyInfoAction extends BaseAction{
	
	@Resource
	private EmergencyInfoService service;
	
	@Resource
	private AreaCodeChineseService accService;
	
	/**
	 * 应急信息播发主页
	 * @Title: page 
	 * @Description: TODO(跳转到应急信息播发主页) 
	 * @param @param model
	 * @param @return
	 * @param @throws Exception    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午11:43:23
	 */
	@GetMapping("/index")  
    public String page(Model model) throws Exception{  
		model.addAttribute("server",serverProperties);
		model.addAttribute("user",getUser());
		model.addAttribute("nvaMenuType","应急播发管理系统");
        return "common/index";  
    }
	/***
	 * 信息管理
	 * @Title: goEmer 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午11:41:57
	 */
	@GetMapping("/goEmer") 
	public String goEmer(Model model) {
		model.addAttribute("emerType", "信息管理");
		return "broadcast/emer_list";
	}
	/**
	 * 信息审核
	 * @Title: goEmerReview 
	 * @Description: TODO(信息审核) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午11:41:46
	 */
	@GetMapping("/goEmerReview") 
	public String goEmerReview(Model model) {
		model.addAttribute("emerType", "信息审核");
		return "broadcast/emerReview_list";
	}
	/**
	 * 信息播发
	 * @Title: goEmerBroadCast 
	 * @Description: TODO(信息播发) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午11:08:48
	 */
	@GetMapping("/goEmerBroadCast") 
	public String goEmerBroadCast(Model model) {
		model.addAttribute("emerType", "信息播发");
		return "broadcast/broadcast_list";
	}
	
	@GetMapping("/goEmerPlan")
	public String goEmerPlan(Model model) {
		
		return "broadcast/emerPlan_list";
	}
	/**
	 * 新增/编辑应急信息
	 * @Title: addEmer 
	 * @Description: TODO(新增/编辑(无id时为新增，有id编辑)应急信息) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午11:41:30
	 */
	@GetMapping("/addEmer")
	public String addEmer(Model model,String type,Integer id) {
		Emergencyinfo emer = null;
		if(id!=null) {
			emer = service.selectById(id);
		}else {
			emer = new Emergencyinfo();
			emer.setStatus(2);
			emer.setSound("60");
			emer.setDuration("60");
		}
		if(type.equals("add")) {
			emer.setFlag(1);
		}else {
			emer.setFlag(0);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("sort","id");
		map.put("order","ASC");
		model.addAttribute("type", type); 
		model.addAttribute("emer", emer);  
		//输出资源（例如：参数配置中的 “麦克风（编码器1）”）
		model.addAttribute("infoSourceList", service.InfosourceList(map));
		 //事件类型集合
		model.addAttribute("accidentTypeList", service.AccidenttypeList(map));
		 //应急事件等级
		model.addAttribute("accidentLevelList", service.AccidentlevelList(map));
		//所有的文字展示方式
		model.addAttribute("displayMethodList", service.DisplaymethodList(map));
		 //展示语言
		model.addAttribute("displayLanguageList", service.DisplaylanguageList(map));
		return "broadcast/emer_edit";
	}
	
	/**
	 * 分页查询应急信息
	 * @Title: queryEmer 
	 * @Description: TODO(分页查询应急信息) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午5:14:20
	 */
	@RequestMapping("/queryEmer")
	@ResponseBody
	public HashMap<String,Object> queryEmer(Page page){
		Map<String,Object> map = page.getMap();
		//map.put("areacode",getUser().getAreaCode());
		map.put("flag", 1);
		map.put("sort", "start_time");
		map.put("order", "DESC");
		List<Emergencyinfo> list = service.queryPage(map);
		int count = service.countPage(map);
		return JsonWrapper.wrapperPage(list,count);
	}
	/**
	 * 分页查询已经播发的应急信息
	 * @Title: queryEmer 
	 * @Description: TODO(分页查询已经播发的应急信息) 
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月1日 下午5:14:20
	 */
	@RequestMapping("/queryBroadcastingEmer")
	@ResponseBody
	public HashMap<String,Object> queryBroadcastingEmer(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		//设置状态为已播发(status=6)
		map.put("status", 6);
		List<Emergencyinfo> list = service.queryPage(map);
		int count = service.countPage(map);
		return JsonWrapper.wrapperPage(list,count);
	}
	/**
	 * 分页查询预案信息
	 * @Title: queryEmerPlan 
	 * @Description: TODO(分页查询预案信息) 
	 * @param @param page
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 下午12:20:37
	 */
	@RequestMapping("/queryEmerPlan")
	@ResponseBody
	public HashMap<String,Object> queryEmerPlan(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		//设置状态为已播发(status=6)
		map.put("flag", 0);
		map.put("sort", "id");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.queryPage(map);
		int count = service.countPage(map);
		return JsonWrapper.wrapperPage(list,count);
	}
	/**
	 * 信息审核list数据
	 * @Title: queryReviewList 
	 * @Description: TODO(信息审核list数据) 
	 * @param @param page
	 * @param @param queryParam
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午8:31:24
	 */
	@RequestMapping("/queryReviewList")
	@ResponseBody
	public HashMap<String,Object> queryReviewList(Page page,String queryParam){
		int flag = queryParam.indexOf("plan")!=-1?0:1;
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		map.put("status",2);
		map.put("flag",flag);
		map.put("sort", "id");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.queryPage(map);
		int count = service.countPage(map);
		return JsonWrapper.wrapperPage(list,count);
	}
	/**
	 * 信息审核
	 * @Title: review 
	 * @Description: TODO(信息审核) 
	 * @param @param emergencyInfo
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午10:55:46
	 */
	@RequestMapping("/review")
	@ResponseBody
	public HashMap<String,Object> review(Emergencyinfo emergencyInfo){
		String msg="";
		try {
			msg = service.review(emergencyInfo);
			return JsonWrapper.successWrapper(msg);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	/**
	 * 保存应急信息
	 * @Title: save 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param info
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月27日 下午4:10:42
	 */
	@RequestMapping("/save")
	@ResponseBody
	public HashMap<String,Object> save(Emergencyinfo info){
		try {
			Date startTime = info.getStartTime();
			if(startTime!=null) {
				long end = info.getStartTime().getTime()+new Integer(info.getDuration())*60*1000;
				info.setEndTime(new Date(end));
			}
			info.setUnitname(serverProperties.getUnitName());
			info.setAreacode(getUserAreaCode());
			//设置事件编码（随机数）
			info.setEmergencycode(EBMessageUtil.generateSendtime());
			info.setAddresscodename(accService.getPcodeChinese(getUserAreaCode()));
			service.save(info);
			return JsonWrapper.successWrapper();
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper();
		}
	}
	/**
	 * 删除应急信息
	 * @Title: deleteEmer 
	 * @Description: TODO(删除应急信息) 
	 * @param @param ids
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午11:07:13
	 */
	@RequestMapping("/deleteEmer")
	@ResponseBody
	public HashMap<String,Object> deleteEmer(Integer[] ids){
		try {
			service.deleteByIds(ids);
			return JsonWrapper.successWrapper();
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	/**
	 * 分页查询信息播发列表
	 * @Title: queryBroadCastList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param page
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月27日 下午4:09:49
	 */
	@RequestMapping("/queryBroadCastList")
	@ResponseBody
	public HashMap<String,Object> queryBroadCastList(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		map.put("status",5);
		map.put("orStatus",6);
		map.put("sort", "status");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.queryPage(map);
		int total = service.countPage(map);
		return JsonWrapper.wrapperPage(list, total);
	}
}
