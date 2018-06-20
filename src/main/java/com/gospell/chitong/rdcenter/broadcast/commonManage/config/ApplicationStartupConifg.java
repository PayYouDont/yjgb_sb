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

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent>{

	@Resource
	private ServerProperties serverProperties;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		heartStart();//项目启动时候执行心跳包发送
	}
	
	public void heartStart() {
		
		Map<String,File> tarMap = new HashMap<>();
		File tar = new File(serverProperties.getHeartSend());
		tarMap.put(tar.getName(), tar);
		int rate = 1500;
		String url = serverProperties.getHeartUrl();
		String heartReceiptPath = serverProperties.getHeartReceipt();
		HeartListener listerner = new HeartListener(tarMap, rate, url, heartReceiptPath);
		listerner.setRate(10000);
		listerner.start();
	}
}
