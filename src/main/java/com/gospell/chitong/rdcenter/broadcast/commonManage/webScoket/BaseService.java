package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * webscoket 基础服务类
 * Created by jiangsx on 18-7-3.
 */
public abstract class BaseService {
    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public String requestMsg = "";
    public static final Map<String, WebSocketRunnable> map = new HashMap<>();

    protected abstract String getMessage(String messageFromClient);
    
    private long sleepDuration = 1L;
    
    protected void setSleepDuration(long l) {
		this.sleepDuration = l;
	}
    
    protected  long getSleepDuration() {
        return sleepDuration;
    }

    protected Session session;

    protected ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
    
    public synchronized void start(Session session) {
        this.session = session;
        WebSocketRunnable webSocketRunnable = new WebSocketRunnable(session.getId());
        threadPool.schedule(webSocketRunnable, 0 , TimeUnit.SECONDS);
        map.put(webSocketRunnable.getId(), webSocketRunnable);
        logger.debug("A new client is connected ! \n\t SessionId is : " + session.getId());
    }

    public synchronized void close(Session session) {
        map.get(session.getId()).flag = false;
        map.remove(session.getId());
        /*try {
            if(session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            logger.error(e.toString());
        }*/
        logger.debug("Client with session id : " + session.getId() + " is closed!");
    }


    public void incoming(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public void onError( Session currentSession, Throwable t) throws Throwable {
        logger.error("WebSocket Error: " + t.toString(), t);
    }

     class WebSocketRunnable implements Runnable {

        private String id;
        private boolean flag = true;
        private String responseMsg = "";


        WebSocketRunnable(String id) {
            this.id = id;
        }

         public String getId() {
             return id;
         }

        @Override
        public void run() {
            if (flag) {
                pushMessage();
                //threadPool.schedule(this, getSleepDuration(), TimeUnit.SECONDS);
            }
        }

        private void pushMessage() {
            try {
                if (session.isOpen() && !requestMsg.equals("")) {
                    responseMsg = getMessage(requestMsg);
                    session.getBasicRemote().sendText(responseMsg);
                    logger.debug("push message : \n\t" + responseMsg + "\n to Client with session id :" + session.getId());
                }
            } catch (IOException e) {
                logger.error(e.toString(), e);
                try {
                    session.close();
                } catch (IOException e1) {
                    logger.error(e.toString(), e);
                }
            }
        }
    }
}
