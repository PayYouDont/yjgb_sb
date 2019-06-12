package com.gospell.chitong.rdcenter.broadcast.netty.entity;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.config.ApplicationStartupConifg;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.EBD_EBM_EmerRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info.EBD_EBRDTInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_EBMBrdLog;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.state.EBD_EBRDTState;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceTaskRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdSendMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.DictionaryRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceTask;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.netty.util.ByteUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.StringUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import lombok.Data;
import lombok.Getter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName ReturnData
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/23 11:53
 **/
@Data
public class ReturnData {
    private byte[] data;
    private byte[] resourcesData;
    private byte[] numberData;
    private byte[] dataType;
    private byte[] workData;
    private String resourceCode;
    public static final int DEV_FAULT=1;//设备故障
    public static final int DEV_FAULT_REPLY=2;//设备故障回复
    public static final int DEV_STATUS_IDLE=1;//设备空闲状态
    public static final int DEV_STATUS_WORK=2;//设备工作状态
    public static final int DEV_STATUS_FAULT=3;//设备故障状态
    public ReturnData(byte[] data){
        this.data = data;
        resourcesData = ByteUtils.subBytes (data,11,12);
        numberData = ByteUtils.subBytes (data,23,2);
        short length = ByteUtils.getShort (numberData);
        int count = length*12;
        dataType = ByteUtils.subBytes (data,25+count,1);
        workData = ByteUtils.subBytes (data,28+count,data.length-32-count);
        resourceCode = String.valueOf (ByteUtils.Bytes2HexString (resourcesData));
    }
    public int saveDevcie(Deviceinfo deviceinfo, EBD ebd) throws Exception{
        if(ebd!=null){
            TarUtil.sendEBD (ebd);
        }
        int i = ApplicationContextRegister.getBean (DeviceInfoService.class).save (deviceinfo);
        ApplicationStartupConifg.updateDeviceMap ();
        return i;
    }
    private Deviceinfo getDeviceInfo(){
        String addressCode = EBDcodeUtil.getAreaCode (resourceCode.substring (1));
        Map<String,Object> map = new HashMap<> ();
        map.put ("devaddresscode",addressCode);
        List<Deviceinfo> deviceinfos = ApplicationContextRegister.getBean (DeviceinfoMapper.class).list (map);
        if(deviceinfos!=null&&deviceinfos.size ()>0){
            return deviceinfos.get(0);
        }
        return null;
    }
    public void save() throws Exception{
        byte type = dataType[0];
        switch (type){
            case 0x10://心跳
                HeartbeatData heartbeatData = new HeartbeatData ();
                heartbeatData.save ();
                return;
            case 0x11://查询指令应答
                CommandResponse response = new CommandResponse ();
                response.save ();
                return;
            case 0x13://终端故障与恢复
                FailureAndRecovery recovery = new FailureAndRecovery ();
                recovery.save ();
                return;
            case 0x14://终端任务切换
                TaskSwitch taskSwitch = new TaskSwitch ();
                taskSwitch.save ();
                return;
            case 0x15://上报播发结果
                BroadcastResult result = new BroadcastResult ();
                result.save ();
                return;
            case 0x16://播发记录上报
                BroadcastRecord record = new BroadcastRecord ();
                record.save ();
                return;
        }
    }
    /**
     * 心跳
     **/
    private class HeartbeatData{
        public byte getWorkStatus(){
            return ByteUtils.subBytes (workData,0,1)[0];
        }
        public byte firstRegist(){
            return ByteUtils.subBytes (workData,1,1)[0];
        }
        public byte devCodeLength(){
            return ByteUtils.subBytes (workData,2,1)[0];
        }
        public String getDevdsn(){
            return ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,3,devCodeLength()));
        }
        public void save() throws Exception{
            String devdsn = getDevdsn ();
            Deviceinfo deviceinfo;
            long currentTime = System.currentTimeMillis ();
            ApplicationStartupConifg.timerMap.put (devdsn,currentTime);
            if(ApplicationStartupConifg.deviceinfoMap.containsKey (devdsn)){//判断缓存中是否存在
                //保存设备信息
                deviceinfo = ApplicationStartupConifg.deviceinfoMap.get (devdsn);
                String status = deviceinfo.getStatus ();
                int statusCode = (int) Math.pow (2,getWorkStatus ());
                String workStatus = StringUtil.patch ("0",8,Integer.valueOf (Integer.toBinaryString (statusCode)));
                if(deviceinfo.getResouceCode ()!=null&&!status.equals (workStatus)){//若注册过且状态更改了就上报到上级平台
                    deviceinfo.setStatus ((int)getWorkStatus());
                    EBD_EBRDTInfo ebrdtInfo = EBD_EBRDTInfo.createInstance (deviceinfo);
                    saveDevcie (deviceinfo,ebrdtInfo);
                }else if(!status.equals (workStatus)){//未注册但是状态更改了
                    deviceinfo.setStatus ((int)getWorkStatus());
                    saveDevcie (deviceinfo,null);
                }

            }else{
                deviceinfo = new Deviceinfo ();
                deviceinfo.setStatus ((int)getWorkStatus());
                deviceinfo.setDevdsn (devdsn);
                deviceinfo.setOnregister ("0");
                saveDevcie (deviceinfo,null);
            }
        }
    }
    /**
    * 查询指令应答
    **/
    private class CommandResponse{
        /**
         * 0：表示终端成功接收并且正确处理；
         * 13：表示请求数据包出现错误；
         * 60：表示终端出现错误，无法处理。
         **/
        public byte getResultCode(){
            return ByteUtils.subBytes (workData,0,1)[0];
        }
        /**
        * 结果描述长度
        **/
        public byte[] getResultDescLength(){
            return ByteUtils.subBytes (workData,1,2);
        }
        /**
         * 结果描述内容
         **/
        public byte[] getResultDesc(){
            short descLength = ByteUtils.getShort (getResultDescLength ());
            if(descLength>0){
                return ByteUtils.subBytes (workData,3,descLength);
            }
            return null;
        }
        /**
        * 查询参数个数
        **/
        public byte getQueryParamNumber(){
            if(getResultDesc ()!=null){
                return ByteUtils.subBytes (workData,3+getResultDesc ().length,1)[0];
            }
            return 0;
        }
        public List<Map<String,byte[]>> getParam(){
            List<Map<String,byte[]>> list = new ArrayList<> ();
            //起始位置
            int offset = 4+getResultDesc ().length;
            for (int i=0;i<getQueryParamNumber ();i++){
                Map<String,byte[]> map = new HashMap<> ();
                byte[] tag = ByteUtils.subBytes (workData,offset,1);
                offset += tag.length;
                map.put ("tag",tag);
                byte[] tagContentLength = ByteUtils.subBytes (workData,offset,1);
                offset += tagContentLength.length;
                map.put ("tagContentLength",tagContentLength);
                byte[] tagContentDesc = ByteUtils.subBytes (workData, offset,tagContentLength[0]);
                offset += tagContentDesc[0];
                map.put ("tagContentDesc",tagContentDesc);
                list.add (map);
            }
            return list;
        }
        public void save(){
            List<Dictionary> dictionaries = ApplicationContextRegister.getBean (DictionaryRepository.class).findAllByField ("tag");
            CmdSendMapper sendMapper = ApplicationContextRegister.getBean (CmdSendMapper.class);
            Map<String,Object> map = new HashMap<> ();
            map.put ("cmdCharLike",resourceCode.substring (1));
            List<CmdSend> sends = sendMapper.list (map);
            if(sends!=null&&sends.size ()>0){
                List<Map<String,byte[]>> params = getParam ();
                StringBuilder builder = new StringBuilder ();
                CmdSend send = sends.get (0);
                dictionaries.forEach (dictionary -> {
                    String key = dictionary.getFieldKey ();
                    String value = dictionary.getFieldValue ();
                    params.forEach ((param)->{
                        String tag = new String (param.get ("tag"));
                        if(key.equals (tag)){
                            String tagContentDesc = new String ( param.get ("tagContentDesc"));
                            builder.append (value+":"+tagContentDesc);
                            builder.append (",");
                        }
                    });
                });
                builder.delete (builder.lastIndexOf (","),builder.length ());
                int resultCode = getResultCode ();
                send.setStatus (resultCode);
                send.setResult (builder.toString ());
                sendMapper.updateByPrimaryKeySelective (send);
            }

        }
    }
    /**
    * 终端故障与恢复
    **/
    private class FailureAndRecovery{
        private String[] faultDescs = {"电源电流过低","平均电源功耗过低","功放输出电压过低","锁定频率场强过低","无法获取监测信息"};
        private Map<Integer,String> codeMap;
        public FailureAndRecovery(){
            codeMap = initCodeMap ();
        }
        //故障恢复标识
        /**
         * 1：发生故障
         * 2：故障消除，即恢复正常
        **/
        public byte getRecoveryCode(){
            return ByteUtils.subBytes (workData,0,1)[0];
        }
        /**
         * 1：电源电流过低
         * 2：平均电源功耗过低
         * 3：功放输出电压过低
         * 4：锁定频率场强过低
         * 5：无法获取监测信息
        **/
        public byte getFailureCode(){
            return ByteUtils.subBytes (workData,1,1)[0];
        }
        /**
         * 发生故障或者故障恢复的描述信息
         **/
        public byte[] getFailureDesc(){
            return ByteUtils.subBytes (workData,2,255);
        }
        /**
         * 发生时间
         **/
        public byte[] getFailureStartTime(){
            return ByteUtils.subBytes (workData,257,4);
        }
        private Map<Integer,String> initCodeMap(){
            Map<Integer,String> map = new HashMap<> ();
            for (int i=0;i<faultDescs.length;i++){
                map.put (i,faultDescs[i]);
            }
            return map;
        }
        public void save() throws Exception{
            Deviceinfo deviceinfo = getDeviceInfo ();
            if(deviceinfo!=null){
                if(getRecoveryCode()==DEV_FAULT){//发生故障
                    deviceinfo.setStatus (DEV_STATUS_FAULT);
                    deviceinfo.setStatusDesc (codeMap.get (getFailureCode ()));
                }else if(getRecoveryCode ()==DEV_FAULT_REPLY){
                    deviceinfo.setStatus (DEV_STATUS_IDLE);
                }
                EBD_EBRDTState state = EBD_EBRDTState.createInstance (deviceinfo);
                saveDevcie (deviceinfo,state);
            }
        }
    }
    /**
    * 终端任务切换指令语法定义
    **/
    private class TaskSwitch{
        /**
        * 切换标识:
        * 1：任务开始
        * 2：任务结束
        **/
        public byte getSwitchTag(){
            return ByteUtils.subBytes (workData,0,1)[0];
        }
        /**
         * 任务类型:
         * 1：应急节目源
         * 2：日常节目源
         * 3：电话
         * 4：短信
         * 5：调音台
         * 6：U 盘
         **/
        public byte getTaskType(){
            return ByteUtils.subBytes (workData,1,1)[0];
        }
        /**
         * 应急广播消息编码
        **/
        public byte[] getEBMId(){
            return ByteUtils.subBytes (workData,2,18);
        }
        /**
         * 发生时间
         **/
        public byte[] getStartDate(){
            return ByteUtils.subBytes (workData,20,4);
        }
        public void save(){
            Deviceinfo deviceinfo = getDeviceInfo ();
            if(deviceinfo!=null){
                DeviceTask task = new DeviceTask ();
                task.setDeviceId (deviceinfo.getId ());
                task.setEBMID (ByteUtils.Bytes2HexString (getEBMId ()).substring (1));
                task.setStatus ((int)getSwitchTag ());
                SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
                try{
                    Date date = sdf.parse (ByteUtils.Bytes2HexString (getStartDate ()));
                    task.setTaskStartTime (date);
                }catch (Exception e){
                    e.printStackTrace ();
                }
                task.setTaskType ((int)getTaskType ());
                ApplicationContextRegister.getBean (DeviceTaskRepository.class).save (task);
            }
        }
    }
    /**
    * 上报播发结果指令
    **/
    private class BroadcastResult{
        /**
         * 应急广播消息编码
         **/
        public byte[] getEBMId(){
            return ByteUtils.subBytes (workData,0,18);
        }
        /**
         * 结果代码:
         * 0：失败；
         * 1：成功。
         **/
        public byte resultCode(){
            return ByteUtils.subBytes (workData,18,1)[0];
        }
        /**
         * 结果描述的长度；
         * 如果无结果描述则长度为 0。
         **/
        public byte[] resultDescLength(){return ByteUtils.subBytes (workData,19,2);}
        /**
        * 结果描述内容
        **/
        public byte[] resultDesc(){
            if(resultDescLength ()[0]>0){
                return ByteUtils.subBytes (workData,21, resultDescLength ().length);
            }
            return null;
        }
        /**
        * 播发开始时间
        **/
        public byte[] startTime(){
            if(resultDescLength ()[0]>0){
                return ByteUtils.subBytes (workData,21+ resultDesc ().length, 4);
            }
            return null;
        }
        /**
        * 播发结束时间
        **/
        public byte[] endTime(){
            if(resultDescLength ()[0]>0){
                return ByteUtils.subBytes (workData,25+ resultDesc ().length, 4);
            }
            return null;
        }
        /**
        * 播发次数
        **/
        public byte[] broadcastNumber(){
            if(resultDescLength ()[0]>0){
                return ByteUtils.subBytes (workData,29+ resultDesc ().length, 1);
            }
            return null;
        }
        /**
         * 上报时间
         **/
        public byte[] reportingTime(){
            if(resultDescLength ()[0]>0){
                return ByteUtils.subBytes (workData,30+ resultDesc ().length, 4);
            }
            return null;
        }
        public void save(){
            String EBMID = ByteUtils.Bytes2HexString (getEBMId ()).substring (1);
            EmergencyinfoMapper emerDao = ApplicationContextRegister.getBean (EmergencyinfoMapper.class);
            Emergencyinfo emergencyinfo = emerDao.getByEbm_id (EBMID);
            if(emergencyinfo!=null){
                Integer flag = emergencyinfo.getFlag ();
                if(flag<2){
                    if(resultCode()==1){//自主播发的应急信息
                        emergencyinfo.setStatus (11);//播发结束
                    }else{
                        emergencyinfo.setStatus (10);//播发失败
                    }
                }else{
                    if(resultCode()==1){//上级转发的应急信息
                        emergencyinfo.setStatus (11);//播发结束
                    }else{
                        emergencyinfo.setStatus (emergencyinfo.getStatus ()+10);//播发失败
                    }
                }
            }
            emergencyinfo.setResult (new String (resultDesc()));
            emerDao.updateByPrimaryKeySelective (emergencyinfo);
        }
    }
    /**
    * 播发记录
    **/
    private class BroadcastRecord{
        /**
        * 前端编码长度
        **/
        private byte getFrontCodeLength(){
            return ByteUtils.subBytes (workData,0,1)[0];
        }
        /**
        * 前端编码 
        **/
        private byte[] getFrontCode(){
            return ByteUtils.subBytes (workData,1,getFrontCodeLength ());
        }
        /**
        * 播发记录数
        **/
        private byte getBroadcastRecordNumber(){
            return ByteUtils.subBytes (workData,1+getFrontCodeLength (),1)[0];
        }
        public void save(){
            /*int index = 2+getFrontCodeLength ();
            EBD_EBMBrdLog log = new EBD_EBMBrdLog ();
            for(int i=0;i<getBroadcastRecordNumber ();i++){
                *//**
                 * 播发的通道号
                 **//*
                String channelNumber = ByteUtils.Bytes2HexString ( ByteUtils.subBytes (workData,index,2));
                index += 2;
                *//**
                 * 0：未处理
                 * 1：等待播发，指未到消息播发时间
                 * 2：播发中
                 * 3：播发成功
                 * 4：播发失败，包括播发全部失败、播发部分失败、未按要求播发等情况
                 * 5：播发取消
                 **//*
                int status = ByteUtils.subBytes (workData,index,1)[0];
                index += 1;
                String EBMID = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,18));
                index += 18;
                *//**
                 * 1：应急节目源；
                 * 2：日常节目源；
                 * 3：电话；
                 * 4：短信；
                 * 5：调音台；
                 * 6：u 盘；
                 * 7：传输覆盖网其他通道（无线）信号
                 **//*
                int taskType = ByteUtils.subBytes (workData,index,1)[0];
                index += 1;
                *//**
                 * 应急广播类型：
                 * 0：保留；
                 * 1：发布系统演练；
                 * 2：模拟演练；
                 * 3：实际演练；
                 * 4：应急广播；
                 * 05：日常广播；
                 * 其他：保留；
                 **//*
                int emerType = ByteUtils.subBytes (workData,index,1)[0];
                index += 1;
                *//**
                 * 应急广播事件级别：
                 * 0：缺省；
                 * 1：1 级（特别重大）；
                 * 2：2 级（重大）；
                 * 3：3 级（较大）；
                 * 4：4 级（一般）；
                 * 其他：保留；
                 **//*
                int eventLevel = ByteUtils.subBytes (workData,index,1)[0];
                index += 1;
                *//**
                 * 应急广播事件类型
                 **//*
                String eventType = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,5));
                index += 5;
                String startTime = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,4));
                index += 4;
                String endTime = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,4));
                index += 4;
                if(taskType==3||taskType==4){
                    int phoneNumberLength = ByteUtils.subBytes (workData,index,1)[0];
                    index += 1;
                    String phoneNumber = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,phoneNumberLength));
                    index += phoneNumberLength;
                }else{
                    *//**
                     * 音量按百分比形式标识，其中：
                     * 0x00：静音
                     * 0xff：开播，音量不变
                     * 0x01～0x64：对应音量 1%～100%
                     * 其他取值无意义
                     **//*
                    int volume = ByteUtils.subBytes (workData,index,1)[0];
                    index += 1;
                    *//**
                     * 应急广播消息覆盖的资源码数量
                     **//*
                    int areaCount = ByteUtils.subBytes (workData,index,1)[0];
                    index += 1;
                    *//**
                     * 资源编码长度
                     **//*
                    int areaCodeLength = ByteUtils.subBytes (workData,index,1)[0];
                    index += 1;
                    for(int j=0;j<areaCodeLength;j++){
                        String areaCode = ByteUtils.Bytes2HexString (ByteUtils.subBytes (workData,index,12));
                        index += 12;
                    }

                }
            }
        */}
    }
}
