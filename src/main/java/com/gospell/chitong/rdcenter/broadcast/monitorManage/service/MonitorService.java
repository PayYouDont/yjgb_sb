/**   
* @Title: MonitorService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.monitorManage.service 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年9月10日 上午9:26:24 
*/
package com.gospell.chitong.rdcenter.broadcast.monitorManage.service;

import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.monitorManage.controller.vo.StatisticsVO;

/** 
* @ClassName: MonitorService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月10日 上午9:26:24 
*  
*/
public interface MonitorService {
	 List<?> getData(StatisticsVO vo);
}
