/**   
* @Title: ISendTarService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.service.Impl 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月3日 下午2:36:47 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.SendTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBDResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
* @ClassName: ISendTarService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月3日 下午2:36:47 
*  
*/
@Service
public class SendTarServiceImpl implements SendTarService{

	@Resource
	private SendTarMapper dao;

	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午2:45:23
	 */
	@Override
	public int delete(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}
	/** 
	 * <p>Title: selectById</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#selectById(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午2:45:23
	 */
	@Override
	public SendTar selectById(String id) {
		return dao.selectByPrimaryKey(id);
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
	 * @date 2019年1月3日 下午2:58:27
	 */
	@Override
	public int save(SendTar record) throws Exception {
		return dao.insertSelective(record);
	}
	/** 
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param record
	 * @param isUpdate
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.SendTarService#save(com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar, boolean) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月3日 下午2:58:27
	 */
	@Override
	public int save(SendTar record, boolean isUpdate) throws Exception {
		if(isUpdate) {
			return dao.updateByPrimaryKey(record);
		}
		return dao.insertSelective(record);
	}
	/** 
	 * <p>Title: saveSendTar</p> 
	 * <p>Description: </p> 
	 * @param ebd
	 * @return
	 * @throws Exception 
	 * @throws
	 * @author peiyongdong
	 * @date 2019年1月3日 下午3:00:25
	 */
	@Override
	public int saveSendTar(EBD ebd, EBD_EBDResponse response) throws Exception {
		SendTar tar = selectById(ebd.getEBD().getEBDID());
		boolean isUpdate = true;
		if (tar == null) {
			isUpdate = false;
			tar = new SendTar();
			tar.setEbdid(ebd.getEBD().getEBDID());
		}
		tar.setEbdType(ebd.getEBD().getEBDType());
		tar.setDestId(ebd.getEBD().getDEST().getEBRID());
		if(response != null) {
			String code = response.getEBD().getEBDResponse().getResultCode();
			tar.setResultCode(new Integer(code));
			tar.setResultDesc(response.getEBD().getEBDResponse().getResultDesc());
		}
		return save(tar, isUpdate);
	}
	
}
