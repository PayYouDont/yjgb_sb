
package com.gospell.chitong.rdcenter.broadcast.commonManage.task;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBMStateResponse;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: HeartJob 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月18日 下午1:45:39 
*  
*/
@Component
public class UpdateDatabasesJob implements Job{
    @Resource
    private EmergencyInfoService emergencyInfoService;
	public static final Logger logger = LoggerFactory.getLogger(UpdateDatabasesJob.class);

	/**
	 * <p>Title: execute</p>
	 * <p>Description: </p>
	 * @param context
	 * @throws JobExecutionException
	 * @see Job#execute(JobExecutionContext)
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月18日 下午1:47:13
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        Map<String,Object> map = new HashMap<> ();
		Integer[] statusList = {5,8};
		map.put("statusList",statusList);//待发送。待播发
        List<Emergencyinfo> emergencyinfos = emergencyInfoService.list (map);
        Date date = new Date ();
        emergencyinfos.forEach (emergencyinfo -> {
            Date startTime = emergencyinfo.getStartTime ();
            Date endTime = emergencyinfo.getEndTime();
			try {
				long startM = startTime.getTime ()-date.getTime ();
				long endM = endTime.getTime()-date.getTime();
				if(startM<=10*1000&&endM>0){//开始时间和现在的时间在10秒以内
					emergencyinfo.setStatus(6);//已发送
					emergencyInfoService.save (emergencyinfo);
					emergencyInfoService.sendEBDByEmer(emergencyinfo.getId(),"1");
				}else if (endM<0){//播发结束时间已经超过当前时间了
					if(emergencyinfo.getFlag()==2){
						emergencyinfo.setStatus(6);//播发结束
					}else {
						emergencyinfo.setStatus(11);//播发结束
					}
					emergencyInfoService.save (emergencyinfo);
					emergencyInfoService.sendEBDByEmer(emergencyinfo.getId(),"1");
				}else if(startM>=0&&emergencyinfo.getStatus()==5){//待发送
					emergencyinfo.setStatus(8);//等待播发
					emergencyInfoService.save (emergencyinfo);
					emergencyInfoService.sendEBDByEmer(emergencyinfo.getId(),"1");
				}
			}catch (Exception e){
				logger.info(e.getMessage(),e);
			}
        });

	}

}
