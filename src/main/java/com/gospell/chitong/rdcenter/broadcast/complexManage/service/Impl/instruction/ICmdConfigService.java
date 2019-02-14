/**   
* @Title: CmdConfigService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月17日 下午5:34:54 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdConfigMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdConfig;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.Command;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.Command.Commands;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

/** 
* @ClassName: CmdConfigService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月17日 下午5:34:54 
*  
*/
@Service
public class ICmdConfigService implements CmdConfigService {

	@Resource
	private CmdConfigMapper dao;
	
	/** 
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月17日 下午5:37:05
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}
	@Override
	public int delete(Integer[] ids) throws Exception {
		int count = 0;
		for (int i = 0; i < ids.length; i++) {
			count += delete(ids[i]);
		}
		return count;
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
	 * @date 2019年1月17日 下午5:37:05
	 */
	@Override
	public int save(CmdConfig record) throws Exception {
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
	 * @date 2019年1月17日 下午5:37:05
	 */
	@Override
	public CmdConfig selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	/** 
	 * <p>Title: list</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService#list(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月18日 下午4:11:59
	 */
	@Override
	public List<CmdConfig> list(Map<String, Object> map) {
		return dao.list(map);
	}

	/** 
	 * <p>Title: count</p> 
	 * <p>Description: </p> 
	 * @param map
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService#count(java.util.Map) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年1月18日 下午4:11:59
	 */
	@Override
	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	/** 
	 * <p>Title: send</p> 
	 * <p>Description: </p> 
	 * @param id
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdConfigService#send(java.lang.Integer) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年2月14日 上午8:34:37
	 */
	@Override
	public int send(Integer[] ids) throws Exception{
		ArrayList<CmdConfig> configs = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			configs.add(selectById(ids[i]));
		}
		if(configs.size()>0) {
			Command command = new Command();
			ArrayList<Commands> comandsList = new ArrayList<>();
			for (CmdConfig config : configs) {
				Commands commands = new Commands();
				commands.setCMD_Tag(config.getTag());
				String cmd = config.getCmd();
				String CMD_Char = JsonUtil.toCMDChar(cmd);
				commands.setCMD_Char(CMD_Char);
				comandsList.add(commands);
			}
			command.setCommands(comandsList);
			String json = JsonUtil.toJson(command);
			System.out.println(json);
			HttpClientUtil.sendPostDataByJson("", json,"utf-8");
		}
		return 0;
	}

}
