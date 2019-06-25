/**   
* @Title: EBD_ConnectionCheck.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午4:47:41 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.response.EBD_EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.HttpClientUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import lombok.EqualsAndHashCode;

import java.util.*;

/** 
* @ClassName: EBD_ConnectionCheck 
* @Description: TODO(心跳检测) 
* @author peiyongdong
* @date 2018年12月13日 下午4:47:41 
*  
*/
@lombok.Data
public class EBD_ConnectionCheck implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private ConnectionCheck ConnectionCheck;
	}
	@lombok.Data
	public static class ConnectionCheck {
		private String RptTime;
	}
	public void init() {
		EBD = new EBD();
		EBD.setEBDHeader();
		EBD.setEBDType("ConnectionCheck");
		EBD.ConnectionCheck = new ConnectionCheck();
		EBD.ConnectionCheck.RptTime = DateUtils.getDateTime();
	}
	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:19:53
	 */
	@Override
	public EBD_EBDResponse creatResponse() {
        try{
            //sendIOQueryCMD();
        }catch (Exception e){
            e.printStackTrace ();
        }
		return null;
	}
    public void sendIOQueryCMD() throws Exception{
        Map<String,Object> map = new HashMap<> ();
        List<Map<String,Object>> commands = new ArrayList<> ();
        Map<String,Object> outputCmd = new LinkedHashMap<> ();
        outputCmd.put ("CMD_Tag",14);
        outputCmd.put ("CMD_Name","Query_Output_Channel");
        Map<String,Object> outputCMD_Data = new LinkedHashMap<> ();
        outputCMD_Data.put ("Output_channel_state",0);
        outputCMD_Data.put ("Output_channel_id",0);
        outputCMD_Data.put ("Front_code",0);
        outputCmd.put ("CMD_Data",outputCMD_Data);
        Map<String,Object> inputCmd = new LinkedHashMap<> ();
        inputCmd.put ("CMD_Tag",15);
        inputCmd.put ("CMD_Name","Query_Input_Channel");
        Map<String,Object> inputCMD_Data = new LinkedHashMap<> ();
        inputCMD_Data.put ("Input_channel_state",0);
        inputCMD_Data.put ("Front_code",2);
        inputCMD_Data.put ("Input_channel_id",2);
        inputCmd.put ("CMD_Data",inputCMD_Data);
        commands.add (outputCmd);
        commands.add (inputCmd);
        map.put ("Commands",commands);
        String json = JsonUtil.toJson (map);
        ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
        String result = HttpClientUtil.sendPostDataByJson(serverProperties.getCmdChannel (), json,"utf-8");
        if(result!=null){
            System.out.println (result);
        }
    }
}
