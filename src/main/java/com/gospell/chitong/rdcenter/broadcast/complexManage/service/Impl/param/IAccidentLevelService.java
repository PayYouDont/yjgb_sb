package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IAccidentLevelService implements AccidentLevelService{
	
	@Resource
	AccidentlevelMapper dao;
	
	@Resource
	private EmergencyinfoMapper emerdao;

	@Override
	public List<Accidentlevel> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService#findById(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午1:44:51
	 */
	@Override
	public Accidentlevel findById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午1:44:51
	 */
	@Override
	@Transactional
	public int save(Accidentlevel entity) throws Exception {
		String userName = ShiroUtils.getUser().getName();
		if(entity.getId()==null) {
			int i = saveCheck(entity);
			if(i!=0) {
				return i;
			}
			entity.setCreateBy(userName);
			return dao.insertSelective(entity);
		}else {
			Map<String,Object> map = new HashMap<>();
			map.put("accidentlevelId", entity.getId());
			int count = emerdao.count(map);
			if(count>0) {//正在被使用
				return -1;
			}
			Accidentlevel level = dao.selectByPrimaryKey(entity.getId());
			if(!level.getLevel().equals(entity.getLevel())) {
				map = new HashMap<>();
				map.put("level", entity.getLevel());
				count = count(map);
				if(count>0) {//名称已经存在
					return -2;
				}
			}
			if(level.getLevelcode()!=entity.getLevelcode()) {
				map.put("levelcode", entity.getLevelcode());
				count = count(map);
				if(count>0) {//编码已经存在
					return -3;
				}
			}
			entity.setUpdateBy(userName);
			return dao.updateByPrimaryKeySelective(entity);
		}
	}

	public int saveCheck(Accidentlevel entity) {
		Map<String,Object> map = new HashMap<>();
		map.put("level", entity.getLevel());
		int count = count(map);
		if(count>0) {//名称已经存在
			return -2;
		}
		map = new HashMap<>();
		map.put("levelcode", entity.getLevelcode());
		count = count(map);
		if(count>0) {//编码已经存在
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
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService#delete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午1:44:51
	 */
	@Override
	public int delete(Integer id) throws Exception {
		if(id!=null) {
			Map<String,Object> map = new HashMap<>();
			map.put("accidentlevelId", id);
			int count = emerdao.count(map);
			if(count>0) {//正在被使用
				return -1;
			}
			return dao.deleteByPrimaryKey(id);
		}
		return 0;
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentLevelService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 下午1:44:51
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
}
