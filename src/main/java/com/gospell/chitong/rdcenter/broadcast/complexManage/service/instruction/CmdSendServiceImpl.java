/**   
* @Title: ICmdSendService.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.service.Impl.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年2月15日 上午11:34:03 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.WhitelistRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.instruction.CmdSendMapper;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Whitelist;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdSend;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CmdType;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.Command;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ICmdSendService
 * @Description: TODO( )
 * @author peiyongdong
 * @date 2019年2月15日 上午11:34:03
 * 
 */
@Service
public class CmdSendServiceImpl implements CmdSendService {
    @Resource
    private CmdSendMapper dao;

    /**
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#delete(java.lang.Object)
	 * @throws @author peiyongdong
	 * @date 2019年2月15日 上午11:34:16
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

    /**
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param record
	 * @return
	 * @throws Exception
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#save(java.io.Serializable)
	 * @throws @author peiyongdong
	 * @date 2019年2月15日 上午11:34:16
	 **/
	@Override
	public int save(CmdSend record) throws Exception {
		if (record.getId() != null) {
			return dao.updateByPrimaryKeySelective(record);
		}
		return dao.insertSelective(record);
	}
	/**
	 * <p>
	 * Title: selectById
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param id
	 * @return
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService#selectById(java.lang.Object)
	 * @throws @author peiyongdong
	 * @date 2019年2月15日 上午11:34:16
     **/
	@Override
	public CmdSend selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	public List<CmdSend> list(Map<String, Object> map) {
		return dao.list(map);
	};

	public int count(Map<String, Object> map) {
		return dao.count(map);
	}

	/**
	 * <p>Title: send</p> 
	 * <p>Description: </p> 
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdSendService#send(java.lang.Integer[]) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年2月18日 上午11:10:56
	 **/
	@Override
	public int send(Integer[] ids) throws Exception {
		ArrayList<CmdSend> sends = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			sends.add(selectById(ids[i]));
		}
        Integer resultStatus = -1;
		if(sends.size()>0) {
			Command command = new Command();
			ArrayList<Command.Commands> comandsList = new ArrayList<>();
			for (CmdSend send : sends) {
				Command.Commands commands = new Command.Commands ();
				commands.setCMD_Tag(send.getTag());
				String cmdChar = send.getCmdChar();
				//CmdType type =
				Map<String,Object> CMD_Char = JsonUtil.toCMDChar(cmdChar);
                commands.setCMD_Name (send.getType ());
				commands.setCMD_Data(CMD_Char);
				comandsList.add(commands);
			}
			command.setCommands(comandsList);
			String json = JsonUtil.toJson(command);
            System.out.println (json);
            ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
			String result = HttpClientUtil.sendPostDataByJson(serverProperties.getCmdChannel (), json,"utf-8");
			if(result.toLowerCase().equals ("ok")){
                sends.forEach (send-> send.setStatus (0));
                resultStatus = 200;
            }else{
                sends.forEach (send-> send.setStatus (13));
                resultStatus = -1;
            }
            save (sends);
		}
		return resultStatus;
	}
	private void save(List<CmdSend> sends){
        sends.forEach (send -> {
            try {
                save (send);
                if(send.getTag ()==12){//白名单
                    Map<String,Object> map = JsonUtil.toCMDChar (send.getCmdChar ());
                    String phoneNumber = map.get ("Phone_number").toString();
                    String userName = map.get ("User_name").toString();
                    Integer permissionTypeId = Integer.valueOf (map.get ("Permission_type").toString());
                    String permissionType = "";
                    if(permissionTypeId==1){
                        permissionType = "短信";
                    }else if(permissionTypeId==2){
                        permissionType = "电话";
                    }else if(permissionTypeId==3){
                        permissionType = "短信和电话";
                    }
                    String permissionAreaCode = map.get ("Permission_area_code").toString();
                    Whitelist whitelist = new Whitelist (phoneNumber,userName,permissionTypeId,permissionType,permissionAreaCode);
                    ApplicationContextRegister.getBean (WhitelistRepository.class).save (whitelist);
                }
            }catch (Exception e){
                e.printStackTrace ();
            }
        });
    }
	/**
	 * <p>Title: delete</p> 
	 * <p>Description: </p> 
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @see com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction.CmdSendService#delete(java.lang.Integer[]) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2019年2月18日 上午11:10:56
	 **/
	@Override
	public int delete(Integer[] ids) throws Exception {
		int count = 0;
		for (int i = 0; i < ids.length; i++) {
			count += delete(ids[i]);
		}
		return count;
	};
}
