package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD;

public interface CMDService{
	
	Class<? extends CMD> getCMDClass(String cmdType);
	
    int delete(Integer id,Class<? extends CMD> clazz) throws Exception;

    int save(CMD record,Class<? extends CMD> clazz) throws Exception;
    
    int save(HttpServletRequest request,Class<? extends CMD> clazz) throws Exception;

    CMD selectById(Integer id,Class<? extends CMD> clazz);
    
    List<CMD> list(Map<String,Object> map,Class<? extends CMD> clazz);
    
    int count(Map<String,Object> map,Class<? extends CMD> clazz);
    
}
