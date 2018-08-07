package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.RoleMenuVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("roleAction")
public class RoleAction extends BaseAction {

    @Resource
    private RoleService service;

    @GetMapping("toList")
    public String toList(){
        return "complex/sys/role_list";
    }
    @RequiresPermissions (value={"sys:role:add","sys:role:edit"},logical=Logical.OR)
    @GetMapping("toEdit")
    public String toEdit(Model model,Integer id){
        Role role = new Role ();
        if(id!=null){
            role = service.findById (id);
        }
        model.addAttribute ("role",role);
        return "complex/sys/role_edit";
    }

    @RequiresPermissions ("sys:role:list")
    @PostMapping("list")
    @ResponseBody
    public HashMap<String,Object> list(Page page,String search){
        Map<String,Object> map = page.getMap ();
        if(search!=null){
            map.put ("nameLike",search);
        }
        List<Role> list = service.list (map);
        int total = service.count (map);
        return JsonWrapper.wrapperPage(list,total);
    }
    @RequiresPermissions (value={"sys:role:add","sys:role:edit"},logical=Logical.OR)
    @PostMapping("save")
    @ResponseBody
    public HashMap<String,Object> save(RoleMenuVO vo){
        try {
            int i = service.save (vo);
            if(i<0){
                return JsonWrapper.failureWrapper("保存错误");
            }
            return JsonWrapper.successWrapper();
        } catch (Exception e) {
            logger.error ("保存角色错误",e);
            return JsonWrapper.failureWrapper(e.getMessage ());
        }
    }

}
