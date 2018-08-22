
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;

/** 
* @ClassName: UserLogService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月20日 下午3:11:56 
*  
*/
public interface UserLogService {
	
	int save(UserLog userLog) throws Exception;
	
	UserLog findByid(Integer id);
	
	int delete(Integer id) throws Exception;
	
	List<UserLog> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
}
