
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ISysLogService implements SysLogService{
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
	public UserLog findByid(Integer id) {
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
			i += dao.deleteByPrimaryKey(ids[i]);
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
			i += dao.deleteByPrimaryKey(log.getId());
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
		map.put("urlmodule","操作模块");
		map.put("urlfunction","操作事件");
		map.put("createTime","访问时间");
		HSSFWorkbook workbook = ExcelUtil.createExcel(list, map);
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(("系统日志" + ".xls").getBytes(), "iso-8859-1")); 
		workbook.write(response.getOutputStream());
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
		if(vo!=null&&vo.getCondition().equals("date")) {
			map.put("startTime", vo.getStartTime());
			map.put("endTime", vo.getEndTime());
		}else if(vo!=null&&vo.getCondition().equals("field")) {
			map.put(vo.getField(), vo.getFieldValue());
		}
		return list(map);
	}

}
