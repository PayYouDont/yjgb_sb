package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.MediaResouceMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.EBD_EBM_EmerRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBMStateResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRDTState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.EBD_EBM_EmerRelationService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.SendTarService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket.WebScoketServer;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.InfosourceMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaylanguageMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Infosource;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmergencyInfoServiceImpl implements EmergencyInfoService {

    @Resource
    private EmergencyinfoMapper dao;
    @Resource
    private EBD_EBM_EmerRelationMapper eeerDao;

    @Resource
    private DisplaylanguageMapper dldao;

    @Resource
    private DisplaymethodMapper dmdao;

    @Resource
    private AccidenttypeMapper atdao;

    @Resource
    private AccidentlevelMapper aldao;

    @Resource
    private InfosourceMapper infodao;

    @Resource
    private MediaResouceMapper mediaDao;

    @Resource
    private ServerProperties serverProperties;
    /*@Resource
    private AreaCodeChineseService accService;*/

    @Override
    public List<Displaylanguage> DisplaylanguageList(Map<String, Object> map) {
        return dldao.list (map);
    }

    @Override
    public List<Displaymethod> DisplaymethodList(Map<String, Object> map) {
        return dmdao.list (map);
    }

    @Override
    public List<Accidenttype> AccidenttypeList(Map<String, Object> map) {
        return atdao.list (map);
    }

    @Override
    public List<Accidentlevel> AccidentlevelList(Map<String, Object> map) {
        return aldao.list (map);
    }

    @Override
    public List<Infosource> InfosourceList(Map<String, Object> map) {
        return infodao.list (map);
    }

    @Override
    public int delete(Integer id) throws Exception {
        int i = dao.deleteByPrimaryKey (id);
        return i;
    }

    @Override
    public int save(Emergencyinfo emer) throws Exception {
        int i;
        if (emer.getId () != null) {
            i = dao.updateByPrimaryKeySelective (emer);
        } else {
            i = dao.insertSelective (emer);
        }
        return i;
    }

    @Override
    public Emergencyinfo selectById(Integer id) {
        Emergencyinfo emer = dao.selectByPrimaryKey (id);
        return emer;
    }

    @Override
    public List<Emergencyinfo> list(Map<String, Object> map) {
        List<Emergencyinfo> list = dao.list (map);
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        int i = dao.count (map);
        return i;
    }

    public String review(Emergencyinfo info) {
        Integer id = info.getId ();
        String emergencyName = info.getEmergencyname ();
        Date startTime = info.getStartTime ();
        info = selectById (id);
        Integer flag = info.getFlag ();
        if (flag == 0) {// 预案信息
            if (startTime == null) {
                return "开始时间不能为空";
            } else {
                info.setStartTime (startTime);
                long endTimeMillion = startTime.getTime () + Long.valueOf (info.getDuration ()) * 60 * 1000;
                Date endTime = new Date (endTimeMillion);
                info.setEndTime (endTime);
                info.setEmergencyname (emergencyName);
                // 将id置空，存入数据库，预案保留
                info.setId (null);
                info.setStatus (5);// 待发送
                info.setFlag (1);
            }
        } else {
            // 审核的是正常的应急消息
            info.setStatus (5);// 待发送
        }
        try {
            save (info);
            return "审核成功";
        } catch (Exception e) {
            return e.getMessage ();
        }
    }

    @Override
    public int deleteByIds(Integer[] ids) throws Exception {
        int result = 0;
        Map<String, Object> map = new HashMap<> ();
        for (int i = 0; i < ids.length; i++) {
            map.put ("emerId", ids[i]);
            List<EBD_EBM_EmerRelation> eeer = eeerDao.list (map);
            if (eeer != null && eeer.size () > 0) {
                eeer.forEach ((eee) -> eeerDao.deleteByPrimaryKey (eee.getId ()));
            }
            result += dao.deleteByPrimaryKey (ids[i]);
        }
        return result;
    }

    /**
     * @return void
     * @Author peiyongdong
     * @Description (发送应急信息)
     * @Date 11:16 2019/4/11
     * @Param [emerId, msgType]
     **/
    public void sendEBDByEmer(Integer emerId, String msgType) throws Exception {
        Emergencyinfo emer = selectById (emerId);
        if (emer == null) {
            throw new NullPointerException ("This id can't find emergency! EmergencyInfo is null!");
        }
        String ebmId = null;
        Map<String,Object> map = new HashMap<> ();
        map.put ("emerId",emerId);
        List<EBD_EBM_EmerRelation> relations = eeerDao.list (map);
        if(relations!=null&&relations.size ()>0){
            ebmId = relations.get (0).getEbmId ();
        }
        EBD_EBM ebd = new EBD_EBM ();
        ebd.setEmergencyinfo (emer, serverProperties, msgType,ebmId);
        sendEBDByEmer (emer, ebd);
    }

    /**
     * @return void
     * @Author peiyongdong
     * @Description (发送应急信息)
     * @Date 13:54 2019/4/12
     * @Param [emerId, ebd]
     **/
    public void sendEBDByEmer(Emergencyinfo emer, EBD_EBM ebd) throws Exception {
        String outPath = serverProperties.getTarOutPath ();
        String tarPath = TarUtil.createXMLTarByBean (ebd, outPath, ebd.getEBD ().getEBDID ());
        String msgType = ebd.getEBD().getEBM().getMsgBasicInfo().getMsgType();
        if(!msgType.equals("2")&&emer.getMediaId ()!=null){
            MediaResouce resouce = mediaDao.selectByPrimaryKey (emer.getMediaId ());
            String fileName = ebd.getEBD ().getEBM ().getMsgContent ().getAuxiliary ().getAuxiliaryDesc ();
            TarUtil.addFileToTar (tarPath,resouce.getFilePath (),fileName);
        }
        String result = null;
        //如果没有超时才发送到终端播发
        Date endTime = emer.getEndTime();
        Date date = new Date ();
        if (endTime.getTime() - date.getTime()>0){
            result = HttpClientUtil.sendPostFile (serverProperties.getSupporterUrl (), tarPath);
        }
        EBD_EBDResponse response = TarUtil.getEBDResponse (result);
        ApplicationContextRegister.getBean (SendTarService.class).saveSendTar (ebd, response);
        //保存关系表
        EBD_EBM_EmerRelation eeer = new EBD_EBM_EmerRelation ();
        eeer.setEmerId (emer.getId ());
        eeer.setEbmId (ebd.getEBD ().getEBM ().getEBMID ());
        eeer.setEbdId (ebd.getEBD ().getEBDID ());
        ApplicationContextRegister.getBean (EBD_EBM_EmerRelationService.class).save (eeer);
        if (response == null) {
            if (!msgType.equals("2")){
                emer.setStatus(10);//播发失败
            }
            save (emer);
            WebScoketServer.initList();
            throw new NullPointerException ("发送"+tarPath+"包到："+serverProperties.getSupporterUrl ()+"后没有收到回执包!");
        }else{
            EBD_EBRDTState state = new EBD_EBRDTState().createFullResponse();
            TarUtil.sendEBDToSuperior(state);
        }
        String resultCode = response.getEBD ().getEBDResponse ().getResultCode ();
        if (!EBD_EBDResponse.SUCCESS.equals (resultCode)) {
            throw new RuntimeException (response.getEBD ().getEBDResponse ().getResultDesc ());
        }
        if(emer.getEbmId ()==null){
            emer.setEbmId (eeer.getEbmId ());
        }
        if (emer.getFlag () == 0) {//预案发送后需要重新审核
            emer.setStatus (2);//待审核
        }
        if (emer.getStatus () == 6) {//已发送
            emer.setStatus (9);//正在播发
        } else if (emer.getStatus () == 9) {//正在播发
            emer.setStatus (12);//取消播发
        }
        save (emer);
        EBD_EBMStateResponse ebmStateResponse = new EBD_EBMStateResponse(emer,null);
        result = TarUtil.sendEBDToSuperior(ebmStateResponse);
        checkAndSaveResponse(result);
    }
    private void checkAndSaveResponse(String result) throws Exception{
        EBD_EBDResponse response  = TarUtil.getEBDResponse (result);
        if (response!=null){
            FileUtil.copyFile(result,serverProperties.getReplyInTarPath(),response.getEBD().getEBDID());
            FileUtil.delete(result);
            String resultCode = response.getEBD ().getEBDResponse ().getResultCode ();
            if (!EBD_EBDResponse.SUCCESS.equals (resultCode)) {
                throw new RuntimeException (response.getEBD ().getEBDResponse ().getResultDesc ());
            }
        }
    }
    @Override
    public Emergencyinfo getByEbm_id(String ebmId){
        return dao.getByEbm_id(ebmId);
    }
}