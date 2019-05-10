/**   
* @Title: IReceiveTarService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 下午3:11:20 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.ReceiveTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.ReceiveTarService;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: IReceiveTarService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 下午3:11:20 
*  
*/
@Service
public class ReceiveTarServiceImpl implements ReceiveTarService{

	@Resource
	private ReceiveTarMapper dao;
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午3:15:13
	 */
	@Override
	public int delete(String id) throws Exception {
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
	 * @date 2019年1月3日 下午3:15:13
	 */
	@Override
	public int save(ReceiveTar record) throws Exception {
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
	 * @date 2019年1月3日 下午3:15:13
	 */
	@Override
	public ReceiveTar selectById(String id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param record
	 * @param isUpdate
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.ReceiveTarService#save(com.gospell.chitong.rdcenter.broadcast.commonManage.entity.ReceiveTar, boolean) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午3:15:13
	 */
	@Override
	public int save(ReceiveTar record, boolean isUpdate) throws Exception {
		if(isUpdate) {
			return dao.updateByPrimaryKeySelective(record);
		}
		return dao.insertSelective(record);
	}

	/** 
	 * <p>Title: saveReceiveTar</p> 
	 * <p>Description: </p> 
	 * @param ebd
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.ReceiveTarService#saveReceiveTar(com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午3:15:13
	 */
	@Override
	public int saveReceiveTar(EBD ebd) throws Exception {
		ReceiveTar tar = selectById(ebd.getEBD().getEBDID());
		boolean isUpdate = true;
		if (tar == null) {
			isUpdate = false;
			tar = new ReceiveTar();
			tar.setId(ebd.getEBD().getEBDID());
		}
		tar.setResourceCode(ebd.getEBD().getSRC().getEBRID());
		tar.setStatus(0);
		tar.setType(1);
		tar.setEbdType(ebd.getEBD().getEBDType());
		// tar.setResourceId();
		return save(tar, isUpdate);
	}

    @Override
    public List<ReceiveTar> list(Map<String, Object> map) {
        return dao.list (map);
    }
}
