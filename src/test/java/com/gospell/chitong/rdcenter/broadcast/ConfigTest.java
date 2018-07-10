package com.gospell.chitong.rdcenter.broadcast;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.util.ProepertyUtil;
@SpringBootTest 
@RunWith(SpringRunner.class)
public class ConfigTest {
	@Test
	public void test2() {
		String propertyPath = "config/test.properties";
		Map<String,Object> map = new HashMap<>();
		map.put("server.manageName", "hello");
		map.put("server.manageLogo", "world");
		ProepertyUtil.writeToProperties(map, propertyPath);
		System.out.println(ProepertyUtil.getPath());
	}
}
