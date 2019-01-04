/**   
* @Title: ReceiveTarService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.service 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 下午3:10:19 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

/** 
* @ClassName: ReceiveTarService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 下午3:10:19 
*  
*/
public interface ReceiveTarService extends BaseService<ReceiveTar,String>{
	
	int save(ReceiveTar record,boolean isUpdate) throws Exception;
	
	int saveReceiveTar(EBD ebd) throws Exception;
	
}
