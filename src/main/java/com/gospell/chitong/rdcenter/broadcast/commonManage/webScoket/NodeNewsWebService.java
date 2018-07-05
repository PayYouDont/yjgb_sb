package com.gospell.chitong.rdcenter.broadcast.commonManage.webScoket;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBM;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonWrapper;


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

	@Override
	protected String getMessage(String path) {
		if(StringUtils.isNotBlank(path)){
			return showNodeNews(path);
		}
		return null;
	}

	/**
	 * 返回推送消息
	 * @param path
	 * @return
	 */
	public String showNodeNews(String path){
		//String path = "D:\\tar\\EBDT_10234000000000001010101010000000000002889_in.tar";
		File tarfile = new File(path);
		EBM ebm = service.getEbmFromTar(tarfile);
		Map<String, Object> map = ebm.getEMBMap();
		List<Map<String, Object>> list = new LinkedList<>();
		list.add(map);
		return JsonUtil.toJson(JsonWrapper.wrapperPage(list,1));
	}
	@OnOpen
	@Override
	public void start(Session session) {
		setSleepDuration(4L);
		super.start(session);
	}

	@OnClose
	@Override
	public void close(Session session) {
		super.close(session);
	}

	@OnMessage
	@Override
	public void incoming(String request) {
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
	public void startPush(){
		super.start(this.session);
	}
}
