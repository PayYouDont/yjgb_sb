package com.gospell.chitong.rdcenter.broadcast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.listener.HeartListener;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class utilTest {
	@Resource
	protected ServerProperties serverProperties;
	@Test
	public void httpUtilTest() {
		Map<String,File>map = new HashMap<>();
		File file = new File(serverProperties.getHeartSend());
		map.put("test", file);
		String url = "http://192.168.3.60:8888";
		try {
			String result = HttpClientUtil.sendPostFile(url, map,"utf-8");
			System.out.println(".........................."+result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test2() {
		Map<String,File> tarMap = new HashMap<>();
		File file = new File(serverProperties.getHeartSend());
		tarMap.put(file.getName(), file);
		int rate = 10000;
		String url = serverProperties.getHeartUrl();
		String heartReceiptPath = serverProperties.getHeartReceipt();
		HeartListener listerner = new HeartListener(tarMap, rate, url, heartReceiptPath);
		listerner.setRate(10000);
		listerner.start();
	}
}
