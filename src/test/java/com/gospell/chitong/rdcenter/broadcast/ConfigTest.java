package com.gospell.chitong.rdcenter.broadcast;

import java.util.HashMap;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.device.DeviceInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gospell.chitong.rdcenter.broadcast.util.ProepertyUtil;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigTest {
    @Resource
    private ServerProperties serverProperties;
    @Value("${spring.resources.static-locations}")
    private String path;
    @Test
    public void test2() {
        //System.out.println (serverProperties);
        System.out.println(path.substring(path.indexOf("file")+5));
    }
}
