package com.gospell.chitong.rdcenter.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.AdministrativeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	@Resource
	AdministrativeService adservice;
	
	@Resource
	com.gospell.chitong.rdcenter.broadcast.commonManage.service.AreaCodeChineseService acservice;
	
	@Test
	public void test() {
		Map<String,Object> map = new HashMap<>();
		List<Administrative> list = adservice.list(map);
		System.out.println(list);
	}
	@Test
	public void test2() {
		String str = acservice.getChinese("445103115203");
		System.out.println(str);
	}
}
