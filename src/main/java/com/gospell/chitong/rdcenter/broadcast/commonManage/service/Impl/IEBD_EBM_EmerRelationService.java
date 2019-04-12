/**   
* @Title: IEBD_EBM_EmerRelationService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月4日 上午11:30:41 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.EBD_EBM_EmerRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.EBD_EBM_EmerRelationService;

/** 
* @ClassName: IEBD_EBM_EmerRelationService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月4日 上午11:30:41 
*  
*/
@Service
public class IEBD_EBM_EmerRelationService implements EBD_EBM_EmerRelationService{

	@Resource
	private EBD_EBM_EmerRelationMapper dao;
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月4日 上午11:30:59
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param record
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#save(java.io.Serializable) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月4日 上午11:30:59
	 */
	@Override
	public int save(EBD_EBM_EmerRelation record) throws Exception {
		String ebmId = record.getEbmId();
		if(StringUtils.isEmpty(ebmId)) {
			throw new NullPointerException("ebmId is null!");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("ebmId", ebmId);
		List<EBD_EBM_EmerRelation> list = list(map);
		if(list!=null&&list.size()>0) {
			record.setId(list.get(0).getId());
			return dao.updateByPrimaryKeySelective(record);
		}
		return dao.insertSelective(record);
	}

	/** 
	 * <p>Title: selectById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#selectById(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月4日 上午11:30:59
	 */
	@Override
	public EBD_EBM_EmerRelation selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

    @Override
    public List<EBD_EBM_EmerRelation> list(Map<String, Object> map) {
        return dao.list (map);
    }
}
