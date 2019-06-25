package com.gospell.chitong.rdcenter.broadcast.broadcastMange.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.AreaCodeChineseService;
import com.gospell.chitong.rdcenter.broadcast.util.EBMessageUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "应急信息管理")
@RestController
@RequestMapping("/emergencyInfoAction")
public class EmergencyInfoAction extends BaseAction{
	
	@Resource
	private EmergencyInfoService service;
	
	@Resource
	private AreaCodeChineseService accService;
	
	@Resource
	private MediaResouceService mrService;
	
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
    public ModelAndView page(Model model) throws Exception{  
		model.addAttribute("server",serverProperties);
		model.addAttribute("user",getUser());
		model.addAttribute("nvaMenuType","应急播发管理系统");
        return new ModelAndView("common/index");  
    }
	/***
	 * 信息管理
	 * @Title: toList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午11:41:57
	 */
	@GetMapping("/toList") 
	public ModelAndView toList(Model model) {
		model.addAttribute("emerType", "信息管理");
		return new ModelAndView("broadcast/emer_list");
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
	@GetMapping("/toReviewList") 
	public ModelAndView toReviewList(Model model) {
		model.addAttribute("emerType", "信息审核");
		return new ModelAndView("broadcast/emerReview_list");
	}
	/**
	 * 信息播发
	 * @Title: toCastList 
	 * @Description: TODO(信息播发) 
	 * @param @param model
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午11:08:48
	 */
	@GetMapping("/toCastList") 
	public ModelAndView toCastList(Model model) {
		model.addAttribute("emerType", "信息播发");
		return new ModelAndView("broadcast/broadcast_list");
	}
	
	@GetMapping("/toPlan")
	public ModelAndView toPlan(Model model) {
		return new ModelAndView("broadcast/emerPlan_list");
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
	@RequiresPermissions(value = {"emer:info:add","emer:info:edit"},logical = Logical.OR)
	@GetMapping("/toEdit")
	@Log("应急信息编辑")
	public ModelAndView toEdit(Model model,String type,Integer id) {
		Emergencyinfo emer = null;
		if(id!=null) {
			emer = service.selectById(id);
		}
		if(emer==null){
		    emer = new Emergencyinfo ();
			emer.setStatus(2);
			emer.setSound("60");
			emer.setDuration("60");
		}
		if(emer.getFlag ()==null){
            if(type.equals("add")) {
                emer.setFlag(1);
            }else {
                emer.setFlag(0);
            }
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
		//媒体资源
		model.addAttribute("mediaResouce",mrService.list(new HashMap<>()));
		return new ModelAndView("broadcast/emer_edit");
	}
    @PostMapping({"/getPrograme"})
    public HashMap<String, Object> getProgrameJson() {
        String url = serverProperties.getProgramAddress();
        try {
            String result = HttpClientUtil.sendPostDataByJson(url, "", "utf8");
            return JsonWrapper.successWrapper(result);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return JsonWrapper.failureWrapper();
        }
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
	@ApiOperation(value="应急信息列表", notes="应急信息列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String")
	})
	@PostMapping("/list")
	public HashMap<String,Object> list(Page page,String search){
		Map<String,Object> map = page.getMap();
		if(search!=null) {
			map.put("nameLike", search);
		}
		map.put("addressCodeLike", EBDcodeUtil.getParentCode (getUserAreaCode()));
		map.put("flagNot", 0);//非预案信息
		map.put("sort", "start_time");
		map.put("order", "DESC");
		List<Emergencyinfo> list = service.list(map);
		int count = service.count(map);
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
	@ApiOperation(value="已播发应急信息列表", notes="已播发应急信息列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String")
	})
	@PostMapping("/queryBroadcastingEmer")
	public HashMap<String,Object> queryBroadcastingEmer(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		//设置状态为已播发(status=6)
		map.put("status", 6);
		List<Emergencyinfo> list = service.list(map);
		int count = service.count(map);
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
	@ApiOperation(value="预案应急信息列表", notes="预案应急信息列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String")
	})
	@PostMapping("/queryEmerPlan")
	public HashMap<String,Object> queryEmerPlan(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		//设置状态为已播发(status=6)
		map.put("flag", 0);
		map.put("sort", "id");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.list(map);
		int count = service.count(map);
		return JsonWrapper.wrapperPage(list,count);
	}
	/**
	 * 信息审核list数据
	 * @Title: reviewList 
	 * @Description: TODO(信息审核list数据) 
	 * @param @param page
	 * @param @param queryParam
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午8:31:24
	 */
	@ApiOperation(value="应急信息审核列表", notes="应急信息审核列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "queryParam", value = "搜索索引", dataType = "String"),
	})
	@PostMapping("/reviewList")
	public HashMap<String,Object> queryReviewList(Page page,String queryParam){
		int flag = queryParam.indexOf("plan")!=-1?0:1;
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		map.put("status",2);
		map.put("flag",flag);
		map.put("sort", "id");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.list(map);
		int count = service.count(map);
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
	@ApiOperation(value="应急信息审核", notes="应急信息审核接口")
	@RequiresPermissions(value = {"emer:info:add","emer:info:edit"},logical = Logical.OR)
	@Log("应急信息审核")
	@PostMapping("/review")
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
	@ApiOperation(value="保存应急信息", notes="保存应急信息接口")
	@RequiresPermissions(value = {"emer:info:add","emer:info:edit"},logical = Logical.OR)
	@Log("应急信息保存")
	@PostMapping("/save")
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
	* @Author peiyongdong
	* @Description ( 已查看上级信息 )
	* @Date 16:25 2019/4/11
	* @Param [id]
	* @return java.util.HashMap<java.lang.String,java.lang.Object>
	**/
    @PostMapping("/viewed")
    public HashMap<String,Object> viewed(Integer id){
     try {
     Emergencyinfo info = service.selectById (id);
     if(info!=null&&info.getFlag ()==2){
     info.setStatus (info.getStatus ()-1);
     service.save(info);
     WebScoketServer.removeToNodeNews (id);
     return JsonWrapper.successWrapper();
     }
     }catch(Exception e) {
     logger.error(e.getMessage(),e);
     }
     return JsonWrapper.failureWrapper();
     }
     /**
     * 删除应急信息
	 * @Title: delete
	 * @Description: TODO(删除应急信息) 
	 * @param @param ids
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月6日 上午11:07:13
	 */
	@ApiOperation(value="删除应急信息", notes="删除应急信息接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "ids", value = "应急信息id", required = true ,dataType = "String")
	})
	@RequiresPermissions("emer:info:delete")
	@Log("删除应急信息")
	@PostMapping("/delete")
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
	 * @Title: castList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param page
	 * @param @return    设定文件 
	 * @return HashMap<String,Object>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月27日 下午4:09:49
	 */
	@ApiOperation(value="播发应急信息列表", notes="播发应急信息列表接口")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String")
	})
	@PostMapping("/castList")
	public HashMap<String,Object> castList(Page page){
		Map<String,Object> map = page.getMap();
		map.put("areacode", getUser().getAreaCode());
		Integer[] statusList = {5,6,7,8,9,10,11,18,19,22,23};
		map.put("statusList",statusList);
		map.put("sort", "status");
		map.put("order", "ASC");
		List<Emergencyinfo> list = service.list(map);
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	
	@PostMapping("/sendEmer")
	public HashMap<String,Object> sendEmer(Integer emerId,String emerType){
		try {
			service.sendEBDByEmer(emerId,emerType);
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
		
	}
	@PostMapping("/stopEmer")
	public HashMap<String,Object> stopEmer(Integer emerId){
		try {
			service.sendEBDByEmer(emerId,"2");
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
}
