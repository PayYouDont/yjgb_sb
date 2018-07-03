package com.gospell.chitong.rdcenter.broadcast;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.EBRPSInfo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class utilTest {
	@Resource
	protected ServerProperties serverProperties;
	@Test
	public void httpUtilTest() {
		try {
			EBRPSInfo.sendEBRPSInfo(serverProperties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
