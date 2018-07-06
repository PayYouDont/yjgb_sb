package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.listener.HeartListener;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 正式运行时开启此功能
		//heartStart();// 项目启动时候执行心跳包发送
	}

	/**
	 * 项目启动后执行心跳发送功能
	 * 
	 * @Title: heartStart
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws @author
	 *             peiyongdong
	 * @date 2018年7月3日 下午3:21:35
	 */
	public void heartStart() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		// 生成心跳包
		String tarPath = ConnectionCheck.createTar(prop);
		Map<String, File> tarMap = new HashMap<>();
		File tar = new File(tarPath);
		tarMap.put(tar.getName(), tar);
		String url = prop.getSendUrl();
		String heartReceiptPath = prop.getTarInPath() + File.separatorChar + tar.getName();
		HeartListener listerner = new HeartListener(tarMap, url, tarPath, heartReceiptPath);
		listerner.start();
	}
}
