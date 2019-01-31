package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CMDService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;

@RestController
@RequestMapping("cmdAction")
public class CMDAction extends BaseAction {

    @Resource
    private CMDService service;

    @GetMapping("/{cmdType}/cmd")
    public ModelAndView toCMD(Model model,@PathVariable("cmdType") String cmdType) {
    	model.addAttribute ( "cmdType", cmdType );
        return new ModelAndView ( "complex/instruction/cmd" );
    }
    @PostMapping("/{cmdType}/list")
    public HashMap<String,Object> list(@PathVariable("cmdType") String cmdType){
    	Map<String,Object> map = new HashMap<>();
		List<CMD> list = service.list(map, service.getCMDClass(cmdType));
		int count = service.count(map, service.getCMDClass(cmdType));
		return JsonWrapper.wrapperPage(list, count);
    }
    
    @GetMapping("/{cmdType}/toEdit")
    public ModelAndView toEdit(Model model, @PathVariable("cmdType") String cmdType, Integer id) {
        CMD cmd = null;
        try {
			Class<? extends CMD> clazz = service.getCMDClass(cmdType);
            if (id != null) {
                cmd = service.selectById ( id, clazz );
            }
            if (cmd == null) {
                cmd = clazz.newInstance ();
            }
            model.addAttribute ( "cmd", cmd );
            model.addAttribute ( "cmdType", cmdType );
            return new ModelAndView ( "complex/instruction/cmd_" + cmdType );
        } catch (Exception e) {
            logger.error ( e.getMessage (), e );
            return new ModelAndView ( "error/error");
        }
    }
    @PostMapping("/{cmdType}/delete")
    public HashMap<String,Object> delete(@PathVariable("cmdType") String cmdType,Integer id){
    	try {
			service.delete(id, service.getCMDClass(cmdType));
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			return JsonWrapper.failureWrapper(e.getMessage());
		}
    }
    @PostMapping("/{cmdType}/save")
    public HashMap<String,Object> timeSave(HttpServletRequest request,@PathVariable("cmdType") String cmdType){
    	try {
			service.save(request, service.getCMDClass(cmdType));
			return JsonWrapper.successWrapper();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonWrapper.failureWrapper(e.getMessage());
		}
    }
}
