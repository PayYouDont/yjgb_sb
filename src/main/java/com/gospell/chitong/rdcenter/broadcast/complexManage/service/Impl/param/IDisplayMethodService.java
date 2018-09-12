package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.param;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IDisplayMethodService implements DisplayMethodService{
	
	@Resource
	private DisplaymethodMapper dao;

	/** 
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService#findById(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午4:03:07
	 */
	@Override
	public Displaymethod selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午4:03:07
	 */
	@Override
	public int save(Displaymethod entity) throws Exception {
		String userName = ShiroUtils.getUser().getName();
		if(entity.getId()==null) {
			int i = saveCheck(entity);
			if(i!=0) {
				return i;
			}
			entity.setCreateBy(userName);
			return dao.insertSelective(entity);
		}else {
			Displaymethod method = selectById(entity.getId());
			Map<String,Object> map = new HashMap<>();
			if(!method.getMethod().equals(entity.getMethod())) {
				map.put("method", entity.getMethod());
				int count = count(map);
				if(count>0) {
					return -2;
				}
			}
			if(!method.getCode().equals(entity.getCode())) {
				map.put("code", entity.getCode());
				int count = count(map);
				if(count>0) {
					return -3;
				}
			}
			entity.setCreateBy(userName);
			return dao.updateByPrimaryKeySelective(entity);
		}
	}

	public int saveCheck(Displaymethod entity) {
		Map<String,Object> map = new HashMap<>();
		map.put("method", entity.getMethod());
		int count = count(map);
		if(count>0) {
			return -2;
		}
		map = new HashMap<>();
		map.put("code", entity.getCode());
		count = count(map);
		if(count>0) {
			return -3;
		}
		return 0;
	}
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午4:03:07
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午4:03:07
	 */
	@Override
	public List<Displaymethod> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayMethodService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午4:03:07
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

}
