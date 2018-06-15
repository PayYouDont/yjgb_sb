package com.gospell.chitong.rdcenter.broadcast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.util.ProepertyUtil;
@SpringBootTest 
@RunWith(SpringRunner.class)
public class ConfigTest {
	@Resource
	private ServerProperties serverconf;
	
	@Test
	public void test() {
		System.out.println(serverconf);
	}
	@Test
	public void test1() {
		File file = new File(ConfigTest.class.getResource("/resources/config/test.properties").getFile());
		System.out.println(file.getPath());
	}
	@Test
	public void test2() {
		String propertyPath = "config/test.properties";
		Map<String,Object> map = new HashMap<>();
		map.put("server.manageName", "hello");
		map.put("server.manageLogo", "world");
		ProepertyUtil.writeToProperties(map, propertyPath);
	}
}
