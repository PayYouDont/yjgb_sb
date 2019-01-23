package com.gospell.chitong.rdcenter.broadcast.complexManage.controller.instruction;

import com.gospell.chitong.rdcenter.broadcast.commonManage.controller.BaseAction;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CMDService;
import com.gospell.chitong.rdcenter.broadcast.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping("cmdAction")
public class CMDAction extends BaseAction {

    @Resource
    private CMDService service;

    @RequestMapping("/{cmdType}/cmd")
    public ModelAndView toCMD(Model model,@PathVariable("cmdType") String cmdType) {
        model.addAttribute ( "cmdType", cmdType );
        return new ModelAndView ( "complex/instruction/cmd" );
    }

    @GetMapping("/{cmdType}/toEdit")
    public ModelAndView toEdit(Model model, @PathVariable("cmdType") String cmdType, Integer id) {
        cmdType = StringUtil.UpCaseFirstLetter ( cmdType );
        String clssStr = "com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD";
        clssStr += cmdType;
        CMD cmd = null;
        try {
            Class<? extends CMD> clazz = (Class<? extends CMD>) getClass ().getClassLoader ().loadClass ( clssStr );
            if (id != null) {
                cmd = service.selectById ( id, clazz );
            }
            if (cmd == null) {
                cmd = clazz.newInstance ();
            }
            System.out.println (cmd);
            model.addAttribute ( "cmd", cmd );
            return new ModelAndView ( "complex/instruction/cmd_" + cmdType );
        } catch (Exception e) {
            logger.error ( e.getMessage (), e );
            return new ModelAndView ( "error/error");
        }

    }
}
