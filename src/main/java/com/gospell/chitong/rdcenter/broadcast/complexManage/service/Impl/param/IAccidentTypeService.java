package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

@Service
public class IAccidentTypeService implements AccidentTypeSevice{
	
	@Resource
	private AccidenttypeMapper dao;
	@Resource
	private EmergencyinfoMapper emerdao;
	
	@Override
	public List<Accidenttype> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param entity
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice#save(com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 上午9:50:01
	 */
	@Override
	@Transactional
	public int save(Accidenttype entity) throws Exception {
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
			map.put("accidenttypeId", entity.getId());
			int count = emerdao.count(map);
			if(count>0) {//正在被使用
				return -1;
			}
			Accidenttype type = findById(entity.getId());
			if(!type.getName().equals(entity.getName())) {
				map = new HashMap<>();
				map.put("name", entity.getName());
				count = count(map);
				if(count>0) {//名称已经存在
					return -2;
				}
			}
			if(!type.getCode().equals(entity.getCode())) {
				map = new HashMap<>();
				map.put("code", entity.getCode());
				count = count(map);
				if(count>0) {//编号已经存在
					return -3;
				}
			}
			entity.setUpdataBy(userName);
			return dao.updateByPrimaryKeySelective(entity);
		}
	}

	public int saveCheck(Accidenttype entity) {
		Map<String,Object> map = new HashMap<>();
		map.put("name", entity.getName());
		int count = count(map);
		if(count>0) {//名称已经存在
			return -2;
		}
		map = new HashMap<>();
		map.put("code", entity.getCode());
		count = count(map);
		if(count>0) {//编码已经存在
			return -3;
		}
		return 0;
	}
	
	/** 
	 * <p>Title: deltete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice#deltete(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 上午9:50:01
	 */
	@Override
	@Transactional
	public int delete(Integer id) throws Exception {
		if(id!=null) {
			Map<String,Object> map = new HashMap<>();
			map = new HashMap<>();
			map.put("accidenttypeId", id);
			int count = emerdao.count(map);
			if(count>0) {//正在被使用
				return -1;
			}
			return dao.deleteByPrimaryKey(id);
		}
		return -2;
	}

	/** 
	 * <p>Title: findById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice#findById(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 上午9:50:01
	 */
	@Override
	public Accidenttype findById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AccidentTypeSevice#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月27日 上午9:50:01
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}
	
	
	
}
