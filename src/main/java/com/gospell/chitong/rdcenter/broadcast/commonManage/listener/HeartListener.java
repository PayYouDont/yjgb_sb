package com.gospell.chitong.rdcenter.broadcast.commonManage.listener;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.HeartXML;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
@Data
@EqualsAndHashCode(callSuper=false)
public class HeartListener extends Thread{
	//状态
	private Integer status;
	//心跳tar包
	@NonNull
	private Map<String,File> tarMap;
	//是否取消监听
	private boolean isCancel = false;
	//监听间隔
	@NonNull
	private Integer rate = 1500;
	//监听节点
	@NonNull
	private String url;
	//节点回执tar包存放路径
	@NonNull
	private String heartReceiptPath;
	//节点发送tar包存放路径
	@NonNull
	private String heartSendPath;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try {
			String result = "";
			logger.info("准备发送心跳包：");
			String url = getUrl();
			String outPath = getHeartReceiptPath();
			Map<String,File> map = getTarMap();
			logger.info("发送节点:"+url);
			logger.info("回执包存放路径:"+outPath);
			Set<String> keys = map.keySet();
			for (String key : keys) {
				logger.info("心跳包名称:"+key);
			}
			while(true&&!isCancel) {
				HeartXML.cheartHeartXMLTar(getHeartSendPath());
				result = HttpClientUtil.sendPostTar(url, map, outPath);
				this.setStatus(result.equals("")?0:1);
				if(getRate()!=null) {
					Thread.currentThread().sleep(getRate());
				}
			}
			logger.info("心跳包发送停止");
		}catch(Exception e) {
			logger.error("心跳包发送错误:"+e);
			if(getRate()!=null) {
				try {
					Thread.currentThread().sleep(getRate());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			run();
		}
	}

	public HeartListener(Map<String, File> tarMap, String url,String heartSendPath, String heartReceiptPath) {
		super();
		this.tarMap = tarMap;
		this.url = url;
		this.heartReceiptPath = heartReceiptPath;
		this.heartSendPath = heartSendPath;
	}
}
