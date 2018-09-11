
package com.gospell.chitong.rdcenter.broadcast.monitorManage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.monitorManage.controller.vo.StatisticsVO;
import com.gospell.chitong.rdcenter.broadcast.monitorManage.dao.ViewEmerLevelMapper;
import com.gospell.chitong.rdcenter.broadcast.monitorManage.service.MonitorService;

/** 
* @ClassName: MonitorService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月10日 上午9:25:00 
*  
*/
@Service
public class IMonitorService implements MonitorService{
	@Resource
	private ViewEmerLevelMapper VELDao;

	/** 
	 * <p>Title: getData</p> 
	 * <p>Description: </p> 
	 * @param vo
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.monitorManage.service.MonitorService#getData(com.gospell.chitong.rdcenter.broadcast.monitorManage.controller.vo.StatisticsVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月10日 上午9:34:06
	 */
	@Override
	public List<?> getData(StatisticsVO vo) {
		Map<String,Object> map = new HashMap<>();
		map.put("startTime", vo.getStartTime());
		map.put("endTime", vo.getEndTime());
		String type = vo.getType();
		if("count".equals(type)) {
			return VELDao.countByDate(map);
		}else if("type".equals(type)) {
			
		}else if("source".equals(type)) {
			
		}
		return null;
	}
	
}
