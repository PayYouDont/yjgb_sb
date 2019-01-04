package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Infosource;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;

public interface EmergencyInfoService extends BaseService<Emergencyinfo,Integer>{
    
    String review(Emergencyinfo info) throws Exception;
    
    int deleteByIds(Integer[] ids) throws Exception;
    
    int saveXML(EBD ebd) throws Exception;
    
    List<Displaylanguage> DisplaylanguageList(Map<String,Object> map);
    
    List<Displaymethod> DisplaymethodList(Map<String,Object> map);
    
    List<Accidenttype> AccidenttypeList(Map<String,Object> map);
   
    List<Accidentlevel> AccidentlevelList(Map<String,Object> map);
    
    List<Infosource> InfosourceList(Map<String,Object> map);
    
    void sendEBDByEmer(Integer emerId,String msgType) throws Exception;
}
