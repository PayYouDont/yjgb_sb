package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceCertificate;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceCertificateService;
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
@RequestMapping("deviceCertificateAction")
public class DeviceCertificateAction extends BaseAction {
    @Resource
    private DeviceCertificateService service;
    @GetMapping("toList")
    public ModelAndView toList(Model model){
        return new ModelAndView ("complex/device/deviceCertificate_list");
    }
    @PostMapping("list")
    public HashMap<String,Object> list(Integer pageIndex, Integer pageSize){
        Page<DeviceCertificate> page = service.page (pageIndex-1,pageSize);
        List<DeviceCertificate> list = page.getContent ();
        int count = service.count ();
        return JsonWrapper.wrapperPage(list,count);
    }
    @GetMapping("toEdit")
    public ModelAndView toEdit(Model model,Integer id){
        DeviceCertificate deviceCertificate = null;
        if(id!=null){
            deviceCertificate = service.selectById (id);
        }
        if(deviceCertificate==null){
            deviceCertificate = new DeviceCertificate ();
            deviceCertificate.setCertificateUrl ("http://124.127.49.183:809/MAV/zcqq");
        }
        model.addAttribute("deviceCertificate",deviceCertificate);
        return new ModelAndView ("complex/device/deviceCertificate_edit");
    }
    @PostMapping("save")
    public HashMap<String,Object> save(DeviceCertificate deviceCertificate){
        try {
            service.saveOrUpdate (deviceCertificate);
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
    @PostMapping("send")
    public HashMap<String,Object> send(Integer id){
        try {
            service.send (id);
            return JsonWrapper.successWrapper ();
        }catch (Exception e){
            logger.error (e.getMessage (),e);
            return JsonWrapper.failureWrapper (e.getMessage ());
        }
    }
}
