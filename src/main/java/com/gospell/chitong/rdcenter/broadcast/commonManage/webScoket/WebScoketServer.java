package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@ServerEndpoint("/webscoket")
public class WebScoketServer {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session; 
    
    public final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static CopyOnWriteArraySet<WebScoketServer> webSocketSet = new CopyOnWriteArraySet<WebScoketServer>();
    /** 
     * 连接建立成功调用的方法*/  
    @OnOpen  
    public void onOpen(Session session) {  
        this.session = session;  
        webSocketSet.add(this);     //加入set中  
        System.out.println("有新连接加入:"+session.getId()); 
        log.info("有新连接加入:"+session.getId());
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
        log.info("有一连接关闭:" +session.getId());
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
}
