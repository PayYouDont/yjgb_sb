/**   
* @Title: CmdConfigService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月17日 下午5:34:54 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;

/**
 * @ClassName: CmdConfigService
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2019年1月17日 下午5:34:54
 * 
 */
public interface CmdConfigService extends BaseService<CmdConfig, Integer> {
	public int send(Integer[] ids) throws Exception;
	public int delete(Integer[] ids) throws Exception;
}
