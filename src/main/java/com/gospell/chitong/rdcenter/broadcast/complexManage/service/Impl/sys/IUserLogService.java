
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.UserLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService;

/** 
* @ClassName: IUserLogService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月22日 上午10:13:29 
*  
*/
@Service
public class IUserLogService implements UserLogService{
	@Resource
	private UserLogMapper dao;

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param userLog
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public int save(UserLog userLog) throws Exception {
		if(userLog.getId()!=null) {
			return dao.updateByPrimaryKeySelective(userLog);
		}else {
			return dao.insertSelective(userLog);
		}
	}

	/** 
	 * <p>Title: findByid</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService#findByid(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public UserLog findByid(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public List<UserLog> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.UserLogService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
