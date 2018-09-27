
package com.gospell.chitong.rdcenter.broadcast.broadcastMange.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.MediaResouce;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.vo.MediaResouceVO;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;

/** 
* @ClassName: MediaResouce 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月25日 下午4:42:27 
*  
*/
public interface MediaResouceService extends BaseService<MediaResouce>{
	public int save(MediaResouceVO vo) throws Exception;
	List<MediaResouce> list(Map<String,Object> map);
	int count(Map<String,Object> map);
}
