/**   
* @Title: ICmdTypeService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月25日 下午3:16:18 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdTypeMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdTypeService;

/** 
* @ClassName: ICmdTypeService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月25日 下午3:16:18 
*  
*/
@Service
public class ICmdTypeService implements CmdTypeService{
	
	@Resource
	private CmdTypeMapper dao;
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月25日 下午3:16:33
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
	 * @date 2019年1月25日 下午3:16:33
	 */
	@Override
	public int save(CmdType record) throws Exception {
		if(record.getId()!=null) {
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
	 * @date 2019年1月25日 下午3:16:33
	 */
	@Override
	public CmdType selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}
	@Override
	public List<CmdType> list(Map<String,Object> map){
    	return dao.list(map);
    };
    
    @Override
	public int count(Map<String,Object> map) {
    	return dao.count(map);
    };
}
