
package com.gospell.chitong.rdcenter.broadcast.commonManage.task;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info.EBD_EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
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
    private EmergencyinfoMapper emerDao;
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
        map.put ("status","5");//待发送
        List<Emergencyinfo> emergencyinfos = emerDao.list (map);
        Date date = new Date ();
        emergencyinfos.forEach (emergencyinfo -> {
            Date startTime = emergencyinfo.getStartTime ();
            if(startTime.getTime ()-date.getTime ()<=10*1000){//开始时间和现在的时间在10秒以内
                emergencyinfo.setStatus (9);//正在播发
            }
        });

	}

}
