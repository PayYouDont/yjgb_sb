package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuRoleRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.RoleMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.PermissionsVO;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.RoleMenuVO;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper dao;
	@Resource
	private MenuMapper menuDao;
	@Resource
	private MenuRoleRelationMapper mrrDao;

	@Override
	public Role selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int save(RoleMenuVO vo) throws Exception {
		String userName = ShiroUtils.getUser().getName();
		Role role = vo.getRole();
		try {
			if (role.getId() == null) {
				role.setCreateBy(userName);
				dao.insertSelective(role);
			} else {
				role.setUpdateBy(userName);
				dao.updateByPrimaryKeySelective(role);
			}
		} catch (Exception e) {
			return -1;
		}
		List<Menu> menus = vo.getMenusList();
		Map<String, Object> map;
		for (Menu menu : menus) {
			PermissionsVO permis = menu.getPermissions();
			MenuRoleRelation mrr = new MenuRoleRelation();
			mrr.setIsAdd(permis.isAdd());
			mrr.setIsDelete(permis.isDelete());
			mrr.setIsModify(permis.isModify());
			mrr.setIsView(permis.isView());
			mrr.setRoleId(role.getId());
			mrr.setMenuId(menu.getId());
			map = new HashMap<>();
			map.put("roleId", role.getId());
			map.put("menuId", menu.getId());
			int count = mrrDao.count(map);
			try {
				if (count > 0) {
					mrr.setUpdateBy(userName);
					mrr.setId(mrrDao.list(map).get(0).getId());
					mrrDao.updateByPrimaryKeySelective(mrr);
				} else {
					mrr.setCreateBy(userName);
					mrrDao.insertSelective(mrr);
				}
			} catch (Exception e) {
				return -2;
			}
		}
		return 0;
	}

	@Override
	public List<Role> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param record
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#save(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年9月11日 下午2:39:10
	 */
	@Override
	public int save(Role record) throws Exception {
		if (record.getId() == null) {
			return dao.insertSelective(record);
		} else {
			return dao.updateByPrimaryKeySelective(record);
		}
	}

}
