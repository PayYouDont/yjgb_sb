package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import javax.annotation.Resource;

import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.netty.config.NettyServer;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.net.InetSocketAddress;
import java.util.*;

@Configuration
public class ApplicationStartupConifg implements ApplicationListener<ContextRefreshedEvent>,DisposableBean{
	@Resource
	private QuartzManager quartzManager;
	@Resource
	private ServerProperties server;
	@Resource
	private TaskMapper taskDao;
	@Value("${deviceType}")
	private Integer deviceType;
    @Value ("${netty.port}")
    private int port;
    @Value("${netty.url}")
    private String url;
    @Autowired
    private NettyServer socketServer;
    public static Map<String, Deviceinfo> deviceinfoMap = new HashMap<> ();
    public static Map<String, Long> timerMap = new HashMap<> ();
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        updateDeviceMap();
		/**
		 * 正式运行时开启此功能（启动时将配置文件中
		 * config/server.properties中的
		 * server.connectionCheck设为true即可）
		 */
		startHeartJob(server.isConnectionCheck());// 项目启动时候执行心跳包发送
		startSignature();
        startNettyServer();
        checkTimeOut();
	}
    public void checkTimeOut(){
        Timer timer = new Timer();
        timer.schedule (new TimerTask () {
            @Override
            public void run() {
                Iterator<Map.Entry<String,Long>> it = timerMap.entrySet ().iterator ();
                while (it.hasNext ()){
                    Map.Entry<String,Long> entry = it.next ();
                    String k = entry.getKey ();
                    Long v = entry.getValue ();
                    Long currentTime = System.currentTimeMillis ();
                    int interval = (int)((currentTime - v)/1000);
                    if(interval>16){
                        setDeviceTimeOut(k);
                        it.remove ();
                    }
                }
            }
        },1000,1000);
    }
    /**
    * @Author peiyongdong
    * @Description ( 更改设备状态至超时状态 )
    * @Date 15:15 2019/5/31
    * @Param [devdsn]
    * @return void
    **/
    private void setDeviceTimeOut(String devdsn){
        Deviceinfo deviceinfo = deviceinfoMap.get (devdsn);
        deviceinfo.setStatus ("00000001");
        ApplicationContextRegister.getBean (DeviceinfoMapper.class).updateByPrimaryKeySelective (deviceinfo);
    }
	public static void updateDeviceMap(){
        Map<String,Object> map = new HashMap<> ();
        List<Deviceinfo> deviceinfos = ApplicationContextRegister.getBean (DeviceinfoMapper.class).list (map);
        deviceinfos.forEach (deviceinfo -> {
            String devdsn = deviceinfo.getDevdsn ();
            deviceinfoMap.put (devdsn,deviceinfo);
        });
    }
    public void startNettyServer(){
	    new Thread (()->{
            InetSocketAddress address = new InetSocketAddress(url, port);
            ChannelFuture future = socketServer.run(address);
            Runtime.getRuntime().addShutdownHook(new Thread(()->socketServer.destroy()));
            future.channel().closeFuture().syncUninterruptibly();
        }).start ();
    }
	public void startSignature() {
		new Thread(()->{
            while(!SignatureUtil.isStart) {
                SignatureUtil.start(deviceType);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

	}
	public void startHeartJob(boolean isCheck){
		if(isCheck) {
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
	/**
	 * <p>Title: destroy</p>
	 * <p>Description: </p>
	 * @throws Exception
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 * @throws
	 * @author peiyongdong
	 * @date 2019年1月16日 上午9:01:59
	 */
	@Override
	public void destroy() throws Exception {

	}
}

