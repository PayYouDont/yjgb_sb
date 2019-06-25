package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceCertificateRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceCertificate;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.UpdateTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName DeviceTaskServiceImpl
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/29 15:53
 **/
@Service
public class DeviceCertificateServiceImpl implements DeviceCertificateService{
    @Resource
    private DeviceCertificateRepository repository;

    @Override
    public List<DeviceCertificate> list() {
        return repository.findAll ();
    }

    @Override
    public int count() {
        return (int)repository.count ();
    }

    @Override
    public void saveOrUpdate(DeviceCertificate entity) throws Exception {
        if(entity.getId ()!=null){
            DeviceCertificate source = selectById (entity.getId ());
            UpdateTool.copyNullProperties(source,entity);
        }
        save (entity);
    }

    @Override
    public Page<DeviceCertificate> page(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort (Sort.Direction.DESC,"id");
        return repository.findAll (PageRequest.of (pageIndex,pageSize,sort));
    }

    @Override
    public int delete(Integer id) throws Exception {
        repository.deleteById (id);
        return 1;
    }

    @Override
    public int save(DeviceCertificate record) throws Exception {
        repository.save (record);
        return 1;
    }

    @Override
    public DeviceCertificate selectById(Integer id) {
        return repository.findById (id).get ();
    }

    @Override
    public void send(Integer id) throws Exception{
        DeviceCertificate certificate = selectById (id);
        if(certificate==null){
            throw new NullPointerException ("找不到该id相关证书");
        }
        certificate.setSendTime (new Date ());
        String url = certificate.getUrl ();
        String code = HttpClientUtil.post (url,"");
        if(code.indexOf ("error")!=-1||code.indexOf ("CertCtx")==-1){
            certificate.setStatus (1);//发送失败
            save (certificate);
            throw new RuntimeException ("从证书平台获取证书失败");
        }
        String list = code.substring (code.lastIndexOf ("LIST=")+5,code.indexOf (",SIGN_CERT"));
        int begin = code.indexOf("<CertCtx>")+9;
        int end = code.indexOf("</CertCtx>");
        List<String> certCtxList= new ArrayList<>();
        while (begin!=-1&&end!=-1){
            String certCtx = code.substring (begin,end);
            certCtxList.add(certCtx);
            code = code.substring(end+9);
            begin = code.indexOf("<CertCtx>")+9;
            end = code.indexOf("</CertCtx>");
        }
        String result = sendCMD (list,certCtxList);
        if(result==null||!result.toLowerCase ().equals ("ok")){//发送失败或者超时
            certificate.setStatus (1);//发送失败
            save (certificate);
            throw new RuntimeException ("证书下发失败:result="+result);
        }
        certificate.setStatus (0);//发送成功
        save (certificate);
    }
    private String sendCMD(String list,List<String> certCtxList) throws Exception{
        Map<String,Object> map = new HashMap<> ();
        List<Map<String,Object>> commands = new ArrayList<> ();
        Map<String,Object> outputCmd = new LinkedHashMap<> ();
        outputCmd.put ("CMD_Tag",64);
        outputCmd.put ("CMD_Name","Set_CertAuth");
        Map<String,Object> CMD_Data = new LinkedHashMap<> ();
        List<String> certAuth = new ArrayList<> ();
        certAuth.add (list);
        CMD_Data.put ("CertAuth",certAuth);
        CMD_Data.put ("Certh",certCtxList);
        outputCmd.put ("CMD_Data",CMD_Data);
        commands.add (outputCmd);
        map.put ("Commands",commands);
        String json = JsonUtil.toJson (map);
        System.out.println(json);
        ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        String result = HttpClientUtil.sendPostDataByJson(serverProperties.getCmdChannel (), json,"utf-8");
        return result;
    }
}
