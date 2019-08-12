package com.gospell.chitong.rdcenter.broadcast;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBMBrdLog;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.state.EBD_EBRASState;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.EBD_EBM;
import com.gospell.chitong.rdcenter.broadcast.util.FileUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import io.netty.handler.codec.http2.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.util.XMLUtil;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class utilTest {
	@Resource
	protected ServerProperties serverProperties;
	/*@Test
	public void httpUtilTest() {
		try {
			EBRPSInfo.sendEBRPSInfo(serverProperties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	@Test
	public void Test() throws Exception{
		EBD_EBMBrdLog log = new EBD_EBMBrdLog().createFullResponse();
		System.out.println(log.getEBD().getEBMBrdLog().getEBMBrdItem().size());
		XMLUtil.createXMLByBean(log,"C:\\Users\\Pay\\Desktop","test");
	}
	/*@Test
	public void test() {
		Class clazz=EBRDTInfo.class;
		System.out.println(clazz.getSimpleName());
	}*/
}
