package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Page;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.RoleMenuVO;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"角色管理"})
@RequestMapping("roleAction")
@RestController
public class RoleAction extends BaseAction {

    @Resource
    private RoleService service;
    
    @GetMapping("toList")
    public ModelAndView toList(){
        return new ModelAndView("complex/sys/role_list");
    }
    @ApiOperation(value="编辑或新增角色", notes="角色编辑接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "角色id", dataType = "String")
    })
    @RequiresPermissions (value={"sys:role:add","sys:role:edit"},logical=Logical.OR)
    @GetMapping("toEdit")
    public ModelAndView toEdit(Model model,Integer id){
        Role role = new Role ();
        if(id!=null){
            role = service.findById (id);
        }
        model.addAttribute ("role",role);
        return new ModelAndView("complex/sys/role_edit");
    }
    @ApiOperation(value="角色列表", notes="角色列表接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true ,dataType = "String"),
        @ApiImplicitParam(name = "search", value = "搜索", dataType = "String"),
    })
    @RequiresPermissions ("sys:role:list")
    @PostMapping("list")
    public HashMap<String,Object> list(Page page,String search){
        Map<String,Object> map = page.getMap ();
        if(search!=null){
            map.put ("nameLike",search);
        }
        List<Role> list = service.list (map);
        int total = service.count (map);
        return JsonWrapper.wrapperPage(list,total);
    }
    

    @ApiOperation(value="保存角色", notes="保存角色接口")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "RoleMenuVO", value = "角色和菜单关系对象", required = true ,dataType = "String"),
    })
    @Log("保存角色")
    @RequiresPermissions (value={"sys:role:add","sys:role:edit"},logical=Logical.OR)
    @PostMapping("save")
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
