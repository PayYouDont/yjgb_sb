
package com.gospell.chitong.rdcenter.broadcast.commonManage.task;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info.EBD_EBRPSInfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_ConnectionCheck;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.TarUtil;

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
		EBD_ConnectionCheck check = new EBD_ConnectionCheck();
		check.init();
		String tarPath = TarUtil.createXMLTarByBean(check, server.getTarOutPath(), check.getEBD().getEBDID());
		String url = server.getSuperiorUrl();
		try {
			String result = HttpClientUtil.sendPostFile(url, tarPath);
			if(!flag) {
				EBD_EBDResponse response = TarUtil.getEBDResponse(result);
				String resultCode = response.getEBD().getEBDResponse().getResultCode();
				if(response!=null&&EBD_EBDResponse.SUCCESS.equals(resultCode)) {
					EBD_EBRPSInfo info = new EBD_EBRPSInfo(server);
					tarPath = TarUtil.createXMLTarByBean(info, server.getTarOutPath(), info.getEBD().getEBDID());
					HttpClientUtil.sendPostFile(url, tarPath);
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error("发送心跳包错误！",e);
		}
	}

}
