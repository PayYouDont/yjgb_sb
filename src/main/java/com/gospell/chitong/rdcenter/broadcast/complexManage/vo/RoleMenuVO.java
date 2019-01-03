package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import java.util.ArrayList;
import java.util.List;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

import lombok.Data;

@Data
public class RoleMenuVO {
    private Integer id;
    private String name;
    private String descript;
    private String menus;

    public List<Menu> getMenusList(){
    	List<Menu> list  = JsonUtil.toJsonArray(getMenus (),Menu.class);
    	List<Menu> menus = new ArrayList<>();
        return  getChildren(list,menus);
    }

	private static List<Menu> getChildren(List<Menu> list,List<Menu> menus){
		for (Menu menu : list) {
			menus.add(menu);
			if(menu.getChildren().size()>0) {
				getChildren(menu.getChildren(), menus);
			}
		}
        return  menus;
    }

    public Role getRole(){
        Role role = new Role();
        role.setId (getId ());
        role.setName (getName ());
        role.setDescript (getDescript ());
        return role;
    }

}
