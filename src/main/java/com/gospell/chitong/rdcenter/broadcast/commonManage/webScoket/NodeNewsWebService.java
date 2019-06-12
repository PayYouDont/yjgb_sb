package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import java.io.File;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;


/**
 * 节点消息 websocket服务端
 * 
 * @author jiangsx
 * @date 2018年07月3日 上午10:01:08
 * @version V1.0
 */
@ServerEndpoint(value = "/websockets")
@Component
public class NodeNewsWebService extends BaseService {
	@Resource
	private NodeService service;
	private String path;
	@Override
	protected String getMessage(String path) {
		if(StringUtils.isNotBlank(path)){
			return showNodeNews();
		}
		return null;
	}

	/**
	 * 返回推送消息
	 * @param
	 * @return
	 */
	public String showNodeNews(){
		File tarfile = new File(this.path);
		EBD ebd = service.getEbmFromTar(tarfile);
		return JsonUtil.toJson(JsonUtil.toJson(ebd));
	}
	@OnOpen
	@Override
	public void start(Session session) {
		setSleepDuration(4L);
		if(this.path != null){  //指定tar包路径不为空 才推送
			super.start(session);
		}
	}

	@OnClose
	@Override
	public void close(Session session) {
	    if(session!=null){
            super.close(session);
        }
	}

	@OnMessage
	@Override
	public void incoming(String request) {
        System.out.println (request);
		super.incoming(request);
	}

	@OnError
	@Override
	public void onError(Session session, Throwable t) throws Throwable {
		super.onError(session, t);
	}

	/**
	 * 主动推送外部调用接口
	 */
	public void startPush(String path){
		this.path=path;
		super.start(this.session);
	}

    /**
     * 外部静态调用
     * @param path
     */
    public static void startpush(String path){
        NodeNewsWebService nodeNewsWebService = ApplicationContextRegister.getBean(NodeNewsWebService.class);
        nodeNewsWebService.startPush(path);
    }
}
