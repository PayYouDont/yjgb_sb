
package com.gospell.chitong.rdcenter.broadcast.commonManage.task;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.info.EBD_EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/** 
* @ClassName: HeartJob 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月18日 下午1:45:39 
*  
*/
@Component
public class HeartJob implements Job{
	
	@Resource
	private ServerProperties server;
	//是否已经主动上报平台信息
	public static boolean flag = false;
	
	public static final Logger logger = LoggerFactory.getLogger(HeartJob.class);
	
	/** 
	 * <p>Title: execute</p> 
	 * <p>Description: </p> 
	 * @param context
	 * @throws JobExecutionException 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月18日 下午1:47:13
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		EBD_ConnectionCheck check = new EBD_ConnectionCheck();
		check.init();
		try {
			String result = TarUtil.sendEBDToSuperior (check);
			if(!flag) {
				EBD_EBDResponse response = TarUtil.getEBDResponse(result);
				if(response==null){
				    return;
                }
				String resultCode = response.getEBD().getEBDResponse().getResultCode();
				if(EBD_EBDResponse.SUCCESS.equals(resultCode)) {
					EBD_EBRPSInfo info = new EBD_EBRPSInfo(server);
                    TarUtil.sendEBDToSuperior (info);
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error("发送心跳包错误！",e);
		}
	}

}
