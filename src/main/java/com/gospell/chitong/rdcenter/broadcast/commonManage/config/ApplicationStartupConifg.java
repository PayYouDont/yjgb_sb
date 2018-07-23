package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.TaskMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ScheduleJob;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.Task;
import com.gospell.chitong.rdcenter.broadcast.commonManage.quartz.QuartzManager;
import com.gospell.chitong.rdcenter.broadcast.commonManage.task.HeartJob;
import com.gospell.chitong.rdcenter.broadcast.util.ScheduleJobUtils;

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private QuartzManager quartzManager;
	
	@Resource
	private TaskMapper taskDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 正式运行时开启此功能
		startHeartJob();// 项目启动时候执行心跳包发送
	}
	public void startHeartJob(){
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
