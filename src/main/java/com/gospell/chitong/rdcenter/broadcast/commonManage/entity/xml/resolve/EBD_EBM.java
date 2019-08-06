/**
 * @Title: EBD_EBM.java
 * @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml
 * @Description: TODO()
 * @author peiyongdong
 * @date 2018年12月13日 下午5:31:14
 */
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.GsonIgnore;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBMStateResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidentlevelMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.AccidenttypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaylanguageMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.param.DisplaymethodMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidentlevel;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Accidenttype;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaylanguage;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Displaymethod;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EBD_EBM
 * @Description: TODO(应急广播)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:31:14
 *
 */
@lombok.Data
public class EBD_EBM implements EBD {
    private EBD EBD;

    @lombok.Data
    @EqualsAndHashCode(callSuper = false)
    public static class EBD extends BaseEBD {
        private EBM EBM;
    }

    @lombok.Data
    public static class RelatedEBD {
        private String EBDID;
    }

    @lombok.Data
    public static class EBM {
        private String EBMVersion;
        private String EBMID;
        private RelatedInfo RelatedInfo;
        private MsgBasicInfo MsgBasicInfo;
        private MsgContent MsgContent;
    }
    @lombok.Data
    private static class RelatedInfo{
        private String EBMID;
    }
    @lombok.Data
    public static class MsgBasicInfo {
        private String MsgType;
        private String SenderName;
        private String SenderCode;
        private String SendTime;
        private String EventType;
        private String Severity;
        private String StartTime;
        private String EndTime;
    }

    @lombok.Data
    public static class MsgContent {
        private String LanguageCode;
        private String MsgTitle;
        private String MsgDesc;
        private String AreaCode;
        private Auxiliary Auxiliary;
        private String ProgramNum;
    }

    @lombok.Data
    public static class Auxiliary {
        private String AuxiliaryType;
        private String AuxiliaryDesc;
        private String Size;
        @GsonIgnore
        private String path;
    }

    @lombok.Data
    public static class Dispatch {
        private String LanguageCode;
        private EBRPS EBRPS;
        private EBRRTS EBRRTS;
        private EBRAS EBRAS;
        private EBRBS EBRBS;
    }

    @lombok.Data
    public static class EBRPS {
        private String EBRID;
    }

    @lombok.Data
    public static class EBRRTS {
        private String EBRID;
    }

    @lombok.Data
    public static class EBRAS {
        private String EBRID;
    }

    @lombok.Data
    public static class EBRBS {
        private String BrdSysType;
        private String BrdSysInfo;
    }

    @Override
    public com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD creatResponse() {
        Emergencyinfo emergencyinfo = createEmer();
        if (emergencyinfo==null){
            return null;
        }
        EmergencyInfoService emergencyInfoService = ApplicationContextRegister.getBean(EmergencyInfoService.class);
        try {
            emergencyInfoService.save(emergencyinfo);
            EBD_EBMStateResponse ebmStateResponse = new EBD_EBMStateResponse(emergencyinfo,null);
            TarUtil.sendEBDToSuperior(ebmStateResponse);
        }catch (Exception e){
           LoggerUtil.log(this.getClass(),e);
        }
        return null;
    }

    //根据应急信息创建实体类
    public void setEmergencyinfo(Emergencyinfo emerInfo, ServerProperties properties, String msgType, String ebmId) {
        EBD = new EBD ();
        EBD.setEBDHeader ();
        EBD.setEBDType ("EBM");
    /*    EBD.RelatedEBD = new RelatedEBD ();
        EBD.RelatedEBD.EBDID = EBDcodeUtil.getBaseEBDID ();*/
        EBD.EBM = new EBM ();
        EBD.EBM.EBMVersion = "1";
        EBD.EBM.EBMID = ebmId == null ? EBDcodeUtil.getEBMID () : ebmId;
        EBD.EBM.MsgBasicInfo = new MsgBasicInfo ();
        /*
         * MsgType:
         * 	1：实际播发,2：取消播发,3：平台演练播发,
         * 	4：前端演练播发,5：终端演练播发
         */
        EBD.EBM.MsgBasicInfo.MsgType = msgType;
        EBD.EBM.MsgBasicInfo.SenderName = properties.getBroadcastName ();
        EBD.EBM.MsgBasicInfo.SenderCode = properties.getSRC_EBRID ();
        EBD.EBM.MsgBasicInfo.SendTime = DateUtils.getDateTime ();
        EBD.EBM.MsgBasicInfo.EventType = emerInfo.getAccidentType ().getCode ();
        EBD.EBM.MsgBasicInfo.Severity = "" + emerInfo.getAccidentLevel ().getLevelcode ();
        EBD.EBM.MsgBasicInfo.StartTime = DateUtils.formatDateTime (emerInfo.getStartTime ());
        EBD.EBM.MsgBasicInfo.EndTime = DateUtils.formatDateTime (emerInfo.getEndTime ());
        EBD.EBM.MsgContent = new MsgContent ();
        EBD.EBM.MsgContent.LanguageCode = emerInfo.getDisplayLanguage ().getShortname ();
        EBD.EBM.MsgContent.MsgTitle = emerInfo.getEmergencyname ();
        EBD.EBM.MsgContent.AreaCode = emerInfo.getAddresscode ();
        if (emerInfo.getProgramId () != null) {
            EBD.EBM.MsgContent.ProgramNum = emerInfo.getProgramId ().toString ();
        }
        EBD.EBM.MsgContent.MsgDesc = emerInfo.getContent ();
        if (emerInfo.getMediaId () != null) {
            Integer mediaId = emerInfo.getMediaId ();
            MediaResouceService mideaService = ApplicationContextRegister.getBean (MediaResouceService.class);
            MediaResouce midea = mideaService.selectById (mediaId);
            EBD.EBM.MsgContent.Auxiliary = new Auxiliary ();
            EBD.EBM.MsgContent.Auxiliary.AuxiliaryType = "2";
            EBD.EBM.MsgContent.Auxiliary.AuxiliaryDesc = "EBDR_" + midea.getFileName ();
            EBD.EBM.MsgContent.Auxiliary.Size = "" + midea.getFileSize ();
        }
    }
    //创建应急信息
    public Emergencyinfo createEmer(){
        Emergencyinfo emergencyinfo;
        EBM ebm = getEBD().getEBM();
        String ebmId;
        if (ebm.getRelatedInfo()!=null){
            ebmId = ebm.getRelatedInfo().getEBMID();
        }else {
            ebmId = ebm.getEBMID();
        }
        EmergencyInfoService emergencyInfoService = ApplicationContextRegister.getBean(EmergencyInfoService.class);
        emergencyinfo = emergencyInfoService.getByEbm_id(ebmId);
        if (emergencyinfo==null){
            emergencyinfo = new Emergencyinfo();
            emergencyinfo.setEbmId(ebmId);
        }
        //上级转发
        emergencyinfo.setFlag(2);
        MsgBasicInfo info = ebm.getMsgBasicInfo();
        if (info.getMsgType().equals("1")){//等待播发
            emergencyinfo.setStatus(5);//:待发送
        }else if (info.getMsgType().equals("2")){//播发取消
            if (emergencyinfo.getId()!=null){
                try {
                    emergencyInfoService.sendEBDByEmer(emergencyinfo.getId(),"2");
                    emergencyinfo.setStatus(12);//播发取消
                }catch (Exception e){
                    LoggerUtil.log(this.getClass(),e);
                }
                return emergencyinfo;
            }else {
                return null;
            }
        }
        //事件类型
        Map<String,Object> map = new HashMap<>();
        map.put("code",info.getEventType());
        AccidenttypeMapper accidenttypeMapper =  ApplicationContextRegister.getBean(AccidenttypeMapper.class);
        List<Accidenttype> accidenttypeList = accidenttypeMapper.list(map);
        Accidenttype at;
        if (accidenttypeList!=null&&accidenttypeList.size()>0){
            at = accidenttypeList.get(0);
        }else{
            at = new Accidenttype ();
            at.setCode (info.getEventType());
            accidenttypeMapper.insertSelective (at);
        }
        emergencyinfo.setAccidenttypeId(at.getId());
        //事件等级
        map = new HashMap<>();
        map.put("levelcode",info.getSeverity());
        AccidentlevelMapper accidentlevelMapper = ApplicationContextRegister.getBean(AccidentlevelMapper.class);
        List<Accidentlevel> accidentlevelList = accidentlevelMapper.list(map);
        Accidentlevel level;
        if (accidentlevelList!=null&&accidentlevelList.size()>0){
            level = accidentlevelList.get(0);
        }else{
            level = new Accidentlevel ();
            level.setLevelcode (new Integer (info.getSeverity()));
            accidentlevelMapper.insertSelective (level);
        }
        emergencyinfo.setAccidentlevelId(level.getId());
        //开始时间
        emergencyinfo.setStartTime(DateUtils.parseDate(info.getStartTime()));
        //结束时间
        emergencyinfo.setEndTime(DateUtils.parseDate(info.getEndTime()));
        //持续时间
        long between = (emergencyinfo.getStartTime ().getTime () - emergencyinfo.getEndTime ().getTime ()) / (1000 * 60);//除以1000是为了转换成秒
        emergencyinfo.setDuration (String.valueOf (Math.abs (between)));
        //音量
        emergencyinfo.setSound ("60");
        //播发语言
        MsgContent msgContent = ebm.getMsgContent();
        map = new HashMap<>();
        map.put("shortname",msgContent.getLanguageCode());
        DisplaylanguageMapper displaylanguageMapper = ApplicationContextRegister.getBean(DisplaylanguageMapper.class);
        List<Displaylanguage> displaylanguageList = displaylanguageMapper.list(map);
        if (displaylanguageList!=null&&displaylanguageList.size()>0){
            emergencyinfo.setDisplaylanguageId(displaylanguageList.get(0).getId());
        }else{
            Displaylanguage dl = new Displaylanguage();
            dl.setLanguage (msgContent.getLanguageCode());
            dl.setShortname (msgContent.getLanguageCode());
            displaylanguageMapper.insertSelective (dl);
            emergencyinfo.setDisplaylanguageId(dl.getId());
        }
        //应急信息标题
        emergencyinfo.setEmergencyname(msgContent.getMsgTitle());
        //应急信息内容
        emergencyinfo.setContent(msgContent.getMsgDesc());
        //覆盖区域
        emergencyinfo.setAddresscode(msgContent.getAreaCode());
        emergencyinfo.setAreacode(msgContent.getAreaCode());
        //输入资源
        if (msgContent.getProgramNum()!=null){
            emergencyinfo.setProgramId(Integer.parseInt(msgContent.getProgramNum()));
        }
        //媒体资源
        Auxiliary auxiliary = msgContent.getAuxiliary();
        String code = null;
        if(auxiliary!=null){
            code = auxiliary.getAuxiliaryType ();
            emergencyinfo.setProgramdescription (auxiliary.getAuxiliaryDesc ());
            map = new HashMap<>();
            map.put("fileName",auxiliary.getAuxiliaryDesc());
            map.put("fileSize",auxiliary.getSize());
            MediaResouceService mediaResouceService = ApplicationContextRegister.getBean (MediaResouceService.class);
            List<MediaResouce> mediaResouceList = mediaResouceService.list(map);
            MediaResouce mediaResouce;
            if (mediaResouceList!=null&&mediaResouceList.size()>0){
                mediaResouce = mediaResouceList.get(0);
            }else{
                mediaResouce = new MediaResouce();
                mediaResouce.setFileType("mp3");
            }
            mediaResouce.setFileSize(Long.valueOf(auxiliary.getSize()));
            mediaResouce.setFileName(auxiliary.getAuxiliaryDesc());
            mediaResouce.setFilePath(auxiliary.getPath());
            mediaResouce.setSource("上级转发");
            try {
                mediaResouceService.save(mediaResouce);
                emergencyinfo.setMediaId(mediaResouce.getId());
            }catch (Exception e){
                LoggerUtil.log(this.getClass(),e);
            }
        }
        Displaymethod dm;
        DisplaymethodMapper displaymethodMapper = ApplicationContextRegister.getBean(DisplaymethodMapper.class);
        if (code != null) {
            map = new HashMap<> ();
            map.put ("code", code);
            List<Displaymethod> list = displaymethodMapper.list (map);
            if (list.size () > 0) {
                dm = list.get (0);
            } else {
                dm = new Displaymethod ();
                dm.setCode (code);
                dm.setMethod ("MP3播发");
                displaymethodMapper.insertSelective (dm);
            }
        } else {
            dm = displaymethodMapper.selectByPrimaryKey (1);
        }
        //展示方式
        emergencyinfo.setDisplaymethodId (dm.getId ());
        //输出资源
        emergencyinfo.setInfosourceId (1);
        //设置事件编码（随机数）
        emergencyinfo.setEmergencycode (EBMessageUtil.generateSendtime ());
        return emergencyinfo;
    }
}
