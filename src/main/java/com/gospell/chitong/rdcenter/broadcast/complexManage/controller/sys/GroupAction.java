package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Group;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.GroupService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName GroupAction
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 15:04
 **/
@RestController
@RequestMapping("groupAction")
public class GroupAction extends BaseAction {
    @Resource
    private GroupService service;
    @GetMapping("toList")
    public ModelAndView toList(){
        return new ModelAndView("complex/sys/group_list");
    }
    @PostMapping("list")
    public HashMap<String,Object> list(Integer pageIndex, Integer pageSize){
        Page<Group> page = service.page (pageIndex-1,pageSize);
        List<Group> list = page.getContent ();
        int count = page.getSize ();
        return JsonWrapper.wrapperPage (list,count);
    }
    @GetMapping("toEdit")
    public ModelAndView toEdit(Model model, Integer id){
        Group group = null;
        if(id!=null){
            group = service.selectById (id);
        }
        if(group==null){
            group = new Group ();
        }
        model.addAttribute("group",group);
        return new ModelAndView ("complex/sys/group_edit");
    }
    @PostMapping("save")
    public HashMap<String,Object> save(Group group){
        try {
            service.saveOrUpdate (group);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper (e.getMessage ());
        }
    }
    @PostMapping("delete")
    public HashMap<String,Object> delete(Integer id){
        try {
            service.delete (id);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper (e.getMessage ());
        }
    }
}
