
package com.gospell.chitong.rdcenter.broadcast.commonManage.task;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;

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
		String tarPath = ConnectionCheck.createTar(server);
		String url = server.getSendUrl();
		String outPath = server.getTarInPath();
		try {
			String result = HttpClientUtil.sendPostTar(url, tarPath, outPath);
			if(!flag&&!result.equals("")) {
				EBRPSInfo.sendEBRPSInfo();
				flag = true;
			}
		} catch (Exception e) {
			logger.error("发送心跳包错误！",e);
		}
	}

}
