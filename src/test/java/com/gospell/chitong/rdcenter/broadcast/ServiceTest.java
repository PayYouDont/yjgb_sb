package com.gospell.chitong.rdcenter.broadcast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.GsonBuilder;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.NodeService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param.Administrative;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.param.AdministrativeService;

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
