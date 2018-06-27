package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.listener.HeartListener;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.HeartXML;

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent>{

	@Resource
	private ServerProperties serverProperties;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//正式运行时开启此功能
		//heartStart();//项目启动时候执行心跳包发送
	}
	
	public void heartStart() {
		String sendPath = serverProperties.getHeartSend();
		//生成心跳包
		HeartXML.createHeartXMLTar(sendPath);
		Map<String,File> tarMap = new HashMap<>();
		File tar = new File(sendPath);
		tarMap.put(tar.getName(), tar);
		String url = serverProperties.getHeartUrl();
		String heartReceiptPath = serverProperties.getHeartReceipt();
		HeartListener listerner = new HeartListener(tarMap,url,sendPath,heartReceiptPath);
		listerner.start();
	}
}
