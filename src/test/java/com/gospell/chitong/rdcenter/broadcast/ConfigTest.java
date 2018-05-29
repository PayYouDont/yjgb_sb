package com.gospell.chitong.rdcenter.broadcast;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
@SpringBootTest 
@RunWith(SpringRunner.class)
public class ConfigTest {
	@Resource
	private ServerProperties serverconf;
	
	@Test
	public void test1() {
		System.out.println(serverconf);
	}
}
