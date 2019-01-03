package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.TaskMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ScheduleJob;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;
import com.gospell.chitong.rdcenter.broadcast.commonManage.quartz.QuartzManager;
import com.gospell.chitong.rdcenter.broadcast.commonManage.task.HeartJob;
import com.gospell.chitong.rdcenter.broadcast.util.ScheduleJobUtils;
import com.gospell.chitong.rdcenter.broadcast.util.SignatureUtil;

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private QuartzManager quartzManager;
	@Resource
	private ServerProperties server;
	@Resource
	private TaskMapper taskDao;
	@Value("${deviceType}")
	private Integer deviceType;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/**
		 * 正式运行时开启此功能（启动时将配置文件中
		 * config/server.properties中的
		 * server.connectionCheck设为true即可）
		 */
		startHeartJob(server.isConnectionCheck());// 项目启动时候执行心跳包发送
		startSignature();
	}
	public void startSignature() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!SignatureUtil.isStart) {
					SignatureUtil.start(deviceType);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	public void startHeartJob(boolean isCheck){
		if(!isCheck) {
			return;
		}
		Task task = taskDao.selectByJobName("heartJob");
		if(task==null) {
			task = new Task();
			task.setCronExpression("0/5 * * * * ?");
			task.setBeanClass(HeartJob.class.getName());
			task.setMethodName("run2");
			task.setIsConcurrent(ScheduleJob.CONCURRENT_IS);
			task.setDescription("心跳检测任务");
			task.setJobGroup("group1");
			task.setJobName("heartJob");
			task.setJobStatus(ScheduleJob.STATUS_RUNNING);
			taskDao.insertSelective(task);
		}
		ScheduleJob job = ScheduleJobUtils.entityToData(task);
		quartzManager.addJob(job);
	}
}

