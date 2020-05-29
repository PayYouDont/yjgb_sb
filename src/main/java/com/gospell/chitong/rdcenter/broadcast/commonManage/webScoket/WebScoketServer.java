package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.NodeNews;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.MediaResouceService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.EBD_EBM_EmerRelationService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.ReceiveTarService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
@Component
@ServerEndpoint("/websocket")
public class WebScoketServer {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private static Session session;
    public final static Logger logger = LoggerFactory.getLogger(WebScoketServer.class);
    
    private static CopyOnWriteArraySet<WebScoketServer> webSocketSet = new CopyOnWriteArraySet<WebScoketServer>();
    private static List<NodeNews> nodeNews = new ArrayList<>();
    public static enum  Status{
        success("200"),   //返回成功 状态
        timeError("-200"), //日期异常  状态
        exception("500");  // 连接异常  状态
        String status;
        Status(String s) {
            this.status=s;
        }
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
    /**
     * 连接建立成功调用的方法*/  
    @OnOpen  
    public void onOpen(Session session) {  
    	WebScoketServer.session = session;  
        webSocketSet.add(this);     //加入set中  
        logger.info("有新连接加入:"+session.getId());
        try {
        	String msg = JsonUtil.toJson(JsonWrapper.successWrapper("连接成功"));
        	sendInfo(msg);
            initList();
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(Session session) {
        webSocketSet.remove(this);  //从set中删除  
        logger.info("有一连接关闭:" +session.getId());
    }  
    /** 
     * 收到客户端消息后调用的方法 
     * 
     * @param message 客户端发送过来的消息*/  
    @OnMessage  
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:" + message);
        //群发消息  
        for (WebScoketServer item : webSocketSet) {  
            try {  
                item.sendMessage(message);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
    }
    /**
     *  
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error) {
        logger.info("发生错误",error);
    }  
    public void sendMessage(String message) throws IOException {  
    	WebScoketServer.session.getBasicRemote().sendText(message);  
    }  
    /** 
     * 群发自定义消息 
     * */  
    public static void sendInfo(String message) throws IOException {
        for (WebScoketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }
    /**
     * 返回推送消息
     * @param
     * @return
     */
    /*public static String showNodeNews(String path){
        try {
            File ebdFile = TarUtil.readTar(path);
            EBD_EBM ebd = (EBD_EBM)XMLUtil.readXMLToBean(ebdFile);
            EBD_EBM.EBM ebm = ebd.getEBD().getEBM();
            //读取xml内容并生成实体类
            File mp3File = TarUtil.readTar(path,".mp3");
            FileUtil.copyFile(path,"E:\\upload\\EBM_media",mp3File.getName());
            if (ebm.getMsgContent()!=null&&ebm.getMsgContent().getAuxiliary()!=null){
                ebm.getMsgContent().getAuxiliary().setPath(path);
            }
            int i;
        	EmergencyInfoService emerService = ApplicationContextRegister.getBean(EmergencyInfoService.class);
            Emergencyinfo emergencyinfo = ebd.createEmer();
            i = emerService.save(emergencyinfo);
            EBD_EBM_EmerRelationService relationService = ApplicationContextRegister.getBean(EBD_EBM_EmerRelationService.class);
            EBD_EBM_EmerRelation relation = new EBD_EBM_EmerRelation();
            relation.setEbdId(ebd.getEBD().getEBDID());
            relation.setEbmId(ebm.getEBMID());
            relation.setEmerId(emergencyinfo.getId());
            relationService.save(relation);
            if(ebm.getMsgBasicInfo().getMsgType().equals("1")){//播发请求
                nodeNews.add(NodeNews.parseEBD(ebd));
            }
            if (i == -1) {
                return Status.timeError.getStatus();
            }else {
                return JsonUtil.toJson(JsonWrapper.wrapperPage(nodeNews,nodeNews.size ()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }*/
    public static void showNodeNews(EBD_EBM ebd_ebm,String tarPath){
        EBD_EBM.EBM ebm = ebd_ebm.getEBD().getEBM();
        try {
            if (ebm.getMsgContent()!=null&&ebm.getMsgContent().getAuxiliary()!=null){
                MediaResouceService service = ApplicationContextRegister.getBean (MediaResouceService.class);
                Integer madiaId = service.saveByTarPath (tarPath);
                ebm.getMsgContent().getAuxiliary().setMediaId (madiaId);
            }
            Emergencyinfo emergencyinfo = ebd_ebm.createEmer ();
            EmergencyInfoService emerService = ApplicationContextRegister.getBean(EmergencyInfoService.class);
            emerService.save(emergencyinfo);
            EBD_EBM_EmerRelationService relationService = ApplicationContextRegister.getBean(EBD_EBM_EmerRelationService.class);
            EBD_EBM_EmerRelation relation = new EBD_EBM_EmerRelation();
            relation.setEbdId(ebd_ebm.getEBD().getEBDID());
            relation.setEbmId(ebm.getEBMID());
            relation.setEmerId(emergencyinfo.getId());
            relationService.save(relation);
        }catch (Exception e){
            logger.error (e.getMessage (),e);
        }
        if(ebm.getMsgBasicInfo().getMsgType().equals("1")){//播发请求
            nodeNews.add(NodeNews.parseEBD(ebd_ebm));
        }
        String data = JsonUtil.toJson(JsonWrapper.wrapperPage(nodeNews,nodeNews.size ()));
        try {
            WebScoketServer.sendInfo(data);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
        }

    }
    /**
     * 外部静态调用
     * @param path
     * @return  "-200" 日期异常错误标识   "500" 连接异常   "200"  正常标识
     */
    /*public static String startpush(String path){
        String data = showNodeNews(path);
        if(data==null){
            return "500";
        }
        if (Status.timeError.getStatus().equals(data)){
            return Status.timeError.getStatus();
        }else {
            if (WebScoketServer.session!=null&&WebScoketServer.session.isOpen()){
                try {
                    WebScoketServer.sendInfo(data);
                } catch (IOException e) {
                    logger.info(e.getMessage(),e);
                    return Status.exception.getStatus();
                }
            }
            return Status.success.getStatus();
        }
    }*/
    /**
    * @Author peiyongdong
    * @Description (从播发列表删除)
    * @Date 17:31 2019/4/11
    * @Param [emerId]
    * @return void
    **/
    public static void removeToNodeNews(Integer emerId) throws Exception{
        Map<String,Object> map = new HashMap<> ();
        map.put ("emerId",emerId);
        List<EBD_EBM_EmerRelation> list = ApplicationContextRegister.getBean (EBD_EBM_EmerRelationService.class).list (map);
        ReceiveTarService receiveTarService = ApplicationContextRegister.getBean (ReceiveTarService.class);
        for(EBD_EBM_EmerRelation eeer:list){
            String ebmId = eeer.getEbmId ();
            for(NodeNews news:nodeNews){
                String EBMID = news.getEBMID ();
                if(ebmId.equals (EBMID)){
                    nodeNews.remove (news);
                    String ebdId = eeer.getEbdId ();
                    ReceiveTar receiveTar = receiveTarService.selectById (ebdId);
                    if(receiveTar!=null){
                        receiveTar.setStatus (1);
                        receiveTarService.save (receiveTar,true);
                    }
                    break;
                }
            }
        }
        String data = JsonUtil.toJson(JsonWrapper.wrapperPage(nodeNews,nodeNews.size ()));
        WebScoketServer.sendInfo (data);
    }
    //初始化上级信息推送list
    public static void initList() throws Exception{
        Map<String,Object> map = new HashMap<> ();
        map.put ("ebdType","EBD");
        map.put ("status",0);
        List<ReceiveTar> receiveTarList = ApplicationContextRegister.getBean (ReceiveTarService.class).list (map);
        String path = ApplicationContextRegister.getBean (ServerProperties.class).getTarInPath ();
        for(ReceiveTar tar:receiveTarList){
            String id = tar.getId ();
            String filePath = path + File.separatorChar + "EBDT_" + id + ".tar";
            File ebdFile = TarUtil.readTar(filePath);
            EBD ebd = XMLUtil.readXMLToBean(ebdFile);
            NodeNews news = NodeNews.parseEBD (ebd);
            if(!nodeNews.contains (news)){
                nodeNews.add(news);
            }
        }
        String data = JsonUtil.toJson(JsonWrapper.wrapperPage(nodeNews,nodeNews.size ()));
        WebScoketServer.sendInfo (data);
    }
}
