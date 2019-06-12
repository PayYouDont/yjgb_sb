package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceTask;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceTaskService;
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
 * @ClassName DeviceTaskAction
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/29 16:03
 **/
@RestController
@RequestMapping("deviceTaskAction")
public class DeviceTaskAction extends BaseAction {
    @Resource
    private DeviceTaskService service;
    @GetMapping("toList")
    public ModelAndView toList(Model model){
        return new ModelAndView ("complex/device/deviceTask_list");
    }
    @PostMapping("list")
    public HashMap<String,Object> list(Integer pageIndex, Integer pageSize){
        Page<DeviceTask> page = service.page (pageIndex-1,pageSize);
        List<DeviceTask> list = page.getContent ();
        int count = service.count ();
        return JsonWrapper.wrapperPage(list,count);
    }
}
