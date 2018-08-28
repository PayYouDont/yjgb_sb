
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO;

/** 
* @ClassName: UserLogService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月20日 下午3:11:56 
*  
*/
public interface SysLogService {
	
	int save(UserLog userLog) throws Exception;
	
	UserLog findByid(Integer id);
	
	int delete(Integer[] ids) throws Exception;
	
	int delete(List<UserLog> list) throws Exception;
	
	int delete(QueryVO vo) throws Exception;
	
	List<UserLog> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	void export(HttpServletResponse response,QueryVO vo) throws IOException;
	
	List<UserLog> list(QueryVO vo);
	
}
