
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.UserLogMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO;
import com.gospell.chitong.rdcenter.broadcast.util.ExcelUtil;

/** 
* @ClassName: IUserLogService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月22日 上午10:13:29 
*  
*/
@Service
public class SysLogServiceImpl implements SysLogService{
	@Resource
	private UserLogMapper dao;

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param userLog
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.UserLogService#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	@Transactional
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.UserLogService#findByid(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public UserLog selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.UserLogService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	@Transactional
	public int delete(Integer[] ids) throws Exception {
		int i = 0;
		for (int j = 0; j < ids.length; j++) {
			i += delete(ids[i]);
		}
		return i;
	}
	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.UserLogService#list(java.util.Map) 
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.UserLogService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月22日 上午10:13:42
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param vo
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#delete(com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 上午11:33:36
	 */
	@Override
	public int delete(QueryVO vo) throws Exception {
		List<UserLog> list = list(vo);
		return delete(list);
	}

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param list
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#delete(java.util.List) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 上午11:47:08
	 */
	@Override
	@Transactional
	public int delete(List<UserLog> list) throws Exception {
		int i = 0;
		for (UserLog log:list) {
			i += delete(log.getId());
		}
		return i;
	}

	/** 
	 * <p>Title: export</p> 
	 * <p>Description: </p> 
	 * @param response
	 * @param vo 
	 * @throws IOException 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#export(javax.servlet.http.HttpServletResponse, com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 下午2:53:58
	 */
	@Override
	public void export(HttpServletResponse response, QueryVO vo) throws IOException {
		List<UserLog> list = list(vo);
		Map<String,String> map = new HashMap<>();
		map.put("userName","操作用户");
		map.put("clientip","用户IP");
		map.put("url","操作模块");
		map.put("urlfunction","操作事件");
		map.put("createTime","访问时间");
		ExcelUtil.writeExcel(response,list,map);
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param vo
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#list(com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 下午2:55:59
	 */
	@Override
	public List<UserLog> list(QueryVO vo) {
		Map<String,Object> map = new HashMap<>();
		return list(map,vo);
	}
	public List<UserLog> list(Map<String,Object> map,QueryVO vo) {
		if(vo!=null&&vo.getCondition().equals("date")) {
			map.put("startTime", vo.getStartTime());
			map.put("endTime", vo.getEndTime());
		}else if(vo!=null&&vo.getCondition().equals("field")) {
			map.put(vo.getField(), vo.getFieldValue());
		}
		return list(map);
	}
	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param page
	 * @param vo
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#list(com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page, com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 下午5:45:27
	 */
	@Override
	public List<UserLog> list(Page page, QueryVO vo) {
		return list(page.getMap(),vo);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param page
	 * @param vo
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService#count(com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page, com.gospell.chitong.rdcenter.broadcast.complexManage.vo.QueryVO) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月28日 下午5:54:39
	 */
	@Override
	public int count(Page page, QueryVO vo) {
		Map<String,Object> map =page.getMap();
		if(vo!=null&&vo.getCondition().equals("date")) {
			map.put("startTime", vo.getStartTime());
			map.put("endTime", vo.getEndTime());
		}else if(vo!=null&&vo.getCondition().equals("field")) {
			map.put(vo.getField(), vo.getFieldValue());
		}
		return count(map);
	}

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月11日 下午2:21:07
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

}
