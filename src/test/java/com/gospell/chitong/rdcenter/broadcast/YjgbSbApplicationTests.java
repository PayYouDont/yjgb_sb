package com.gospell.chitong.rdcenter.broadcast;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.test.dao.UserInfoMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YjgbSbApplicationTests {

	@Resource
	private UserInfoMapper dao;
	@Test
	public void contextLoads() {
		System.out.println(dao.selectByPrimaryKey((long) 1));
	}

}
