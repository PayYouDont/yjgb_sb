package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.MenuRoleRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.MenuRoleRelation;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.MenuService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.PermissionsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuMapper dao;
	@Resource
	private MenuRoleRelationMapper mrrDao;

	@Override
	public int delete(Integer id) throws Exception{
		int i = dao.deleteByPrimaryKey(id);
		Map<String,Object> map = new HashMap<>();
		map.put("menuId", id);
		List<MenuRoleRelation> mrrs = mrrDao.list(map);
		if(mrrs!=null&&mrrs.size()>0) {
			for (MenuRoleRelation mrr:mrrs){
                i += mrrDao.deleteByPrimaryKey(mrr.getId());
            }
		}
		return i;
	}


	@Override
	public Menu selectById(Integer id) {
		Menu menu = dao.selectByPrimaryKey(id);
		return menu;
	}

	@Override
	public List<Menu> getTree(Integer roleId) {
		List<Menu> pMenus= dao.getRootMenu();
		for (Menu menu : pMenus) {
			getChildren(menu,roleId);
		}
		return pMenus;
	}
	/**
	 * 递归设置Children
	 * @Title: getChildren 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param menu
	 * @param @return    设定文件 
	 * @return Menu    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月8日 上午9:39:21
	 */
	public Menu getChildren(Menu menu,Integer roleId) {
		Integer pid = menu.getId();
		Map<String,Object> map = new HashMap<>();
		map.put("pid", pid);
		map.put("order", "number");
		map.put("sort", "ASC");
		List<Menu> children = list(map);
		menu.setChildren(children);
		if(children.size()>0) {
			for (Menu childmenu : children) {
				if(roleId!=null){
					map = new HashMap<>();
					map.put ("roleId",roleId);
					map.put("menuId",childmenu.getId ());
					int count = mrrDao.count (map);
					if(count>0){//将对应目录权限勾上
						MenuRoleRelation mrr = mrrDao.list (map).get (0);
						PermissionsVO vo = new PermissionsVO ();
						vo.setAdd (mrr.getIsAdd ());
						vo.setDelete (mrr.getIsDelete ());
						vo.setModify (mrr.getIsModify ());
						vo.setView (mrr.getIsView ());
						if(vo.hasPermissionAll ()){
							childmenu.setChecked (true);
						}
						childmenu.setPermissions (vo);
					}
				}
				getChildren(childmenu,roleId);
			}
		}else {
			menu.setState("open");
		}
		return menu;
	}

	@Override
	public List<Menu> list(Map<String, Object> map) {
		return dao.list(map);
	}


	@Override
	public int save(Menu menu) throws Exception{
		int i = 0;
		if(menu.getId()!=null) {
			i = dao.updateByPrimaryKeySelective(menu);
		}else {
			i = dao.insertSelective(menu);
		}
		return i;
	}
}
