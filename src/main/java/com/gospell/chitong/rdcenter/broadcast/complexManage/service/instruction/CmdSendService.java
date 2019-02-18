/**   
* @Title: CmdSendService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年2月15日 上午11:32:24 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;

/** 
* @ClassName: CmdSendService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年2月15日 上午11:32:24 
*  
*/
public interface CmdSendService extends BaseService<CmdSend, Integer>{
	public int send(Integer[] ids) throws Exception;
	public int delete(Integer[] ids) throws Exception;
}
