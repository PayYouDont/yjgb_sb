package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.in.EBM;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Infosource;

public interface EmergencyInfoService {
	int deleteById(Integer id) throws Exception;

    int save(Emergencyinfo record) throws Exception;

    Emergencyinfo selectById(Integer id);
    
    List<Emergencyinfo> queryPage(Map<String,Object> map);
    
    int countPage(Map<String,Object> map);
    
    String review(Emergencyinfo info) throws Exception;
    
    int deleteByIds(Integer[] ids) throws Exception;
    
    String getEmerJson(Emergencyinfo emer) throws Exception;
    
    int saveXML(EBM ebmxml) throws Exception;
    
    List<Displaylanguage> DisplaylanguageList(Map<String,Object> map);
    List<Displaymethod> DisplaymethodList(Map<String,Object> map);
    List<Accidenttype> AccidenttypeList(Map<String,Object> map);
    List<Accidentlevel> AccidentlevelList(Map<String,Object> map);
    List<Infosource> InfosourceList(Map<String,Object> map);
}
