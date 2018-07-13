package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
@Component
@ServerEndpoint("/webscoket")
public class WebScoketServer {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session; 
    
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static CopyOnWriteArraySet<WebScoketServer> webSocketSet = new CopyOnWriteArraySet<WebScoketServer>();

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
        this.session = session;  
        webSocketSet.add(this);     //加入set中  
        System.out.println("有新连接加入:"+session.getId());
        logger.info("有新连接加入:"+session.getId());
        try {
        	sendInfo("连接成功");
        }catch(IOException e) {
        	e.printStackTrace();
        }
    }
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(Session session) {
        System.out.println("有一连接关闭:" +session.getId());
        webSocketSet.remove(this);  //从set中删除  
        logger.info("有一连接关闭:" +session.getId());
    }  
    /** 
     * 收到客户端消息后调用的方法 
     * 
     * @param message 客户端发送过来的消息*/  
    @OnMessage  
    public void onMessage(String message, Session session) {  
        System.out.println("来自客户端的消息:" + message);  
  
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
        System.out.println("发生错误");  
        error.printStackTrace();  
    }  
    public void sendMessage(String message) throws IOException {  
        this.session.getBasicRemote().sendText(message);  
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
    public static String showNodeNews(String path){
        File tarfile = new File(path);
        EBM ebm = ApplicationContextRegister.getBean(NodeService.class).getEbmFromTar(tarfile);
        Map<String, Object> map = ebm.getEMBMap();
        List<Map<String, Object>> list = new LinkedList<>();
        list.add(map);
        int i=0;
        try {
           i = ApplicationContextRegister.getBean(EmergencyInfoService.class).saveXML(ebm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == -200)
            return Status.timeError.getStatus();
        else
            return JsonUtil.toJson(JsonWrapper.wrapperPage(list,1));
    }

    /**
     * 外部静态调用
     * @param path
     * @return  "-200" 日期异常错误标识   "500" 连接异常   "200"  正常标识
     */
    public static String startpush(String path){
        WebScoketServer webScoketServer = ApplicationContextRegister.getBean(WebScoketServer.class);
        String data = showNodeNews(path);
        if (data.equals(Status.timeError.getStatus())){
            return Status.timeError.getStatus();
        }else {
            if (webScoketServer.session.isOpen()){
                try {
                    WebScoketServer.sendInfo(data);
                } catch (IOException e) {
                    e.printStackTrace();
                    return Status.exception.getStatus();
                }
            }
            return Status.success.getStatus();
        }
    }
}
