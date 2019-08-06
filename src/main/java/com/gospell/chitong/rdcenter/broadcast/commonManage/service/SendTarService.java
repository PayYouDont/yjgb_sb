/**   
* @Title: SendTarService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.service 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 下午2:34:29 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;


/** 
* @ClassName: SendTarService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 下午2:34:29 
*  
*/
public interface SendTarService extends BaseService<SendTar,String>{
	
	 int save(SendTar record,boolean isUpdate) throws Exception;
	 
	 int saveSendTar(EBD ebd, EBD_EBDResponse response) throws Exception;
}
