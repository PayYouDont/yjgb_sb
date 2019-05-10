package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Group;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.DictionaryService;
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
 * @ClassName DictionaryAction
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 11:43
 **/
@RestController
@RequestMapping("dictionaryAction")
public class DictionaryAction extends BaseAction {
    @Resource
    private DictionaryService service;
    @Resource
    private GroupService groupService;
    @GetMapping("toList")
    public ModelAndView toList(){
        return new ModelAndView("complex/sys/dictionary_list");
    }
    @PostMapping("list")
    public HashMap<String,Object> list(Integer pageIndex,Integer pageSize){
        Page<Dictionary> page = service.page (pageIndex-1,pageSize);
        List<Dictionary> list = page.getContent ();
        int count = page.getSize ();
        return JsonWrapper.wrapperPage(list,count);
    }
    @GetMapping("toEdit")
    public ModelAndView toEdit(Model model,Integer id){
        Dictionary dictionary = null;
        if(id!=null){
            dictionary = service.selectById (id);
        }
        if(dictionary==null){
            dictionary = new Dictionary ();
        }
        List<Group> groups = groupService.list ();
        model.addAttribute("dictionary",dictionary);
        model.addAttribute ("groups",groups);
        return new ModelAndView ("complex/sys/dictionary_edit");
    }
    @PostMapping("save")
    public HashMap<String,Object> save(Dictionary dictionary){
        try {
            service.saveOrUpdate (dictionary);
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
    @PostMapping("listByField")
    public List<Dictionary> listByField(String field,String except){
        List<Dictionary> dictionaries = service.listByField (field);
        for(Dictionary dictionary:dictionaries){
            if (dictionary.getFieldKey ().equals (except)){
                dictionaries.remove (dictionary);
                break;
            }
        }
        return dictionaries;
    }
}
