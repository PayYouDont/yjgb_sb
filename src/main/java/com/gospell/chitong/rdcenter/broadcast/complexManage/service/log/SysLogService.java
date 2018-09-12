
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO;

/** 
* @ClassName: UserLogService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月20日 下午3:11:56 
*  
*/
public interface SysLogService extends BaseService<UserLog>{
	
	int delete(Integer[] ids) throws Exception;
	
	int delete(List<UserLog> list) throws Exception;
	
	int delete(QueryVO vo) throws Exception;
	
	int count(Page page,QueryVO vo);
	
	void export(HttpServletResponse response,QueryVO vo) throws IOException;
	
	List<UserLog> list(QueryVO vo);
	
	List<UserLog> list(Page page,QueryVO vo);
	
}
