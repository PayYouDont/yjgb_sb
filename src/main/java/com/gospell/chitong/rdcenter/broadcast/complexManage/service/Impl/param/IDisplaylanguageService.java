package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaylanguageMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IDisplaylanguageService implements DisplayLanguageService{

	@Resource
	private DisplaylanguageMapper dao;

	/** 
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService#findById(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午3:00:37
	 */
	@Override
	public Displaylanguage selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午3:00:37
	 */
	@Override
	public int save(Displaylanguage entity) throws Exception {
		String userName = ShiroUtils.getUser().getName();
		if(entity.getId()==null) {
			int i = saveCheck(entity);
			if(i!=0) {
				return i;
			}
			entity.setCreateBy(userName);
			return dao.insertSelective(entity);
		}else {
			Displaylanguage language = selectById(entity.getId());
			Map<String,Object> map = new HashMap<>();
			if(!language.getLanguage().equals(entity.getLanguage())) {
				map.put("language", entity.getLanguage());
				int count = count(map);
				if(count>0) {
					return -2;
				}
			}
			if(!language.getShortname().equals(entity.getShortname())) {
				map = new HashMap<>();
				map.put("shortname", entity.getShortname());
				int count = count(map);
				if(count>0) {
					return -3;
				}
			}
			entity.setUpdateBy(userName);
			return dao.updateByPrimaryKey(entity);
		}
	}

	public int saveCheck(Displaylanguage entity) {
		Map<String,Object> map = new HashMap<>();
		map.put("language", entity.getLanguage());
		int count = count(map);
		if(count>0) {
			return -2;
		}
		map = new HashMap<>();
		map.put("shortname", entity.getShortname());
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午3:00:37
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午3:00:37
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.DisplayLanguageService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午3:00:51
	 */
	@Override
	public List<Displaylanguage> list(Map<String, Object> map) {
		return dao.list(map);
	}
	
	
}
