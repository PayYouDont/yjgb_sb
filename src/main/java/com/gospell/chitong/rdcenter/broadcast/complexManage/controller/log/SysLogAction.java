
package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @ClassName: LogAction 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月28日 上午9:31:18 
*  
*/
@Api(tags = "日志管理")
@RestController
@RequestMapping("sysLogAction")
public class SysLogAction extends BaseAction{
	@Resource
	private SysLogService service;
	
	@GetMapping("toList")
	public ModelAndView toList() {
		return new ModelAndView("complex/log/sysLog_list");
	}
	@PostMapping("list")
	public HashMap<String,Object> list(Page page,QueryVO vo) {
		Map<String,Object> map = page.getMap();
		List<UserLog> list;
		if(vo!=null) {
			list = service.list(vo);
		}else {
			list = service.list(map);
		}
		int total = service.count(map);
		return JsonWrapper.wrapperPage(list, total);
	}
	@ApiOperation(value="删除日志", notes="删除日志接口")
	@RequiresPermissions("log:system:delete")
	@Log("删除日志")
	@PostMapping("delete")
	public HashMap<String,Object> delete(Integer[] ids,QueryVO vo){
		try {
			if(ids!=null&&ids.length>0) {
				service.delete(ids);
				return JsonWrapper.successWrapper();
			}
			if(vo!=null){
				service.delete(vo);
				return JsonWrapper.successWrapper();
			}
			return JsonWrapper.failureWrapper("删除条件为空");
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
	}
	@ApiOperation(value="导出日志", notes="导出日志接口")
	@Log("导出日志")
	@GetMapping("export")
	public void export(HttpServletResponse response,QueryVO vo) {
		try {
			service.export(response, vo);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
