package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Menu;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleMenuVO {
    private Integer id;
    private String name;
    private String descript;
    private String menus;

    public List<Menu> getMenusList(){
        JSONArray array = JSONArray.fromObject (getMenus ());
        List<Menu> list = new ArrayList<> ();
        list =  getChildren(array,list);
        return list;
    }

    @SuppressWarnings("unchecked")
	public static List<Menu> getChildren(JSONArray array,List<Menu> list){
           for(int i=0;i<array.size ();i++){
               List<Menu> menus = JSONArray.toList (array,new Menu(),new JsonConfig ());
               list.addAll (menus);
               Object obj =  array.getJSONObject (i).get ("children");
               if(obj instanceof JSONArray){
                   JSONArray arr = (JSONArray)obj;
                   if(arr.size ()>0){
                       getChildren(arr,list);
                   }
               }

           }

        return  list;
    }

    public Role getRole(){
        Role role = new Role();
        role.setId (getId ());
        role.setName (getName ());
        role.setDescript (getDescript ());
        return role;
    }

}
