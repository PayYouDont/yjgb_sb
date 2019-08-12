/**   
* @Title: EBD_EBMBrdLog.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:32:23 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.dao.EmergencyinfoMapper;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.EBD_EBM_EmerRelationMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.dao.SendTarMapper;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.SendTar;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;
import com.gospell.chitong.rdcenter.broadcast.util.LoggerUtil;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EBD_EBMBrdLog
 * @Description: TODO(播发记录)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:32:23
 * 
 */
@lombok.Data
public class EBD_EBMBrdLog implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBMBrdLog EBMBrdLog;
	}
	
	@lombok.Data
	public static class EBMBrdLog {
		private Params Params;
		private List<EBMBrdItem> EBMBrdItem;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
	}

	@lombok.Data
	public static class EBMBrdItem {
		private EBM EBM;
		private UnitInfo UnitInfo;
		private String BrdStateCode;
		private String BrdStateDesc;
		private Coverage Coverage;
		private ResBrdInfo ResBrdInfo;
	}

	@lombok.Data
	public static class EBM {
		private String EBMID;
		private MsgBasicInfo MsgBasicInfo;
		private MsgContent MsgContent;
	}

	@lombok.Data
	public static class MsgBasicInfo {
		private String MsgType;
		private String SenderName;
		private String SenderCode;
		private String SendTime;
		private String EventType;
		private String Severity;
		private String StartTime;
		private String EndTime;
	}

	@lombok.Data
	public static class MsgContent {
		private String LanguageCode;
		private String MsgTitle;
		private String MsgDesc;
		private String AreaCode;
		private String ProgramNum;
	}

	@lombok.Data
	public static class UnitInfo {
		private Unit Unit;
	}

	@lombok.Data
	public static class Unit {
		private EBRPS EBRPS;
		private String UnitId;
		private String UnitName;
		private String PersonID;
		private String PersonName;
	}

	@lombok.Data
	public static class EBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class Coverage {
		private String CoverageRate;
		private String AreaCode;
		private String ResBrdStat;
	}

	@lombok.Data
	public static class ResBrdInfo {
		private List<ResBrdItem> ResBrdItem;
	}
	@lombok.Data
	public static class ResBrdItem {
		private EBRPS EBRPS;
	}
	@Override
    public EBD_EBMBrdLog createFullResponse() {
		//以下层级结构参考播发记录xml格式
		EBD = new EBD_EBMBrdLog.EBD ();
		EBD.setEBDHeader();
		EBD.setEBDType("EBMBrdLog");
		EBD.EBMBrdLog= new EBMBrdLog ();
		EBD.EBMBrdLog.EBMBrdItem = new ArrayList<> ();
		SendTarMapper sendDao = ApplicationContextRegister.getBean (SendTarMapper.class);
		Map<String,Object> map = new HashMap<> ();
		map.put ("ebdType","EBM");
		List<SendTar> list = sendDao.list (map);
		if(list!=null&&list.size ()>0){
			EBD_EBM_EmerRelationMapper EEEDao = ApplicationContextRegister.getBean (EBD_EBM_EmerRelationMapper.class);
			EmergencyinfoMapper EMERDao = ApplicationContextRegister.getBean (EmergencyinfoMapper.class);
			ServerProperties serverProperties = ApplicationContextRegister.getBean (ServerProperties.class);
			list.forEach ((sendTar)->{
				EBMBrdItem item = new EBMBrdItem ();
				EBD_EBMBrdLog.EBM EBM = new EBD_EBMBrdLog.EBM ();
				String ebdId = sendTar.getEbdid ();
				map.put ("ebdId",ebdId);
				List<EBD_EBM_EmerRelation> EEERList = EEEDao.list (map);
				if(EEERList!=null&&EEERList.size ()>0){
					EBD_EBM_EmerRelation EEER = EEERList.get (0);
					String EBMID = EEER.getEbmId ();
					EBM.setEBMID (EBMID);
					Emergencyinfo emergencyinfo = EMERDao.getByEbm_id (EBMID);
					if (emergencyinfo!=null){
						EBD_EBMBrdLog.MsgBasicInfo msgBasicInfo = new EBD_EBMBrdLog.MsgBasicInfo ();
						msgBasicInfo.setMsgType (String.valueOf(emergencyinfo.getStatusToEBM()));
						msgBasicInfo.setSenderName (serverProperties.getUnitName());
						msgBasicInfo.setSenderCode (emergencyinfo.getAreacode ());
						msgBasicInfo.setSendTime (DateUtils.formatDateTime (sendTar.getSendDate ()));
						msgBasicInfo.setEventType (emergencyinfo.getAccidentType ().getCode ());
						msgBasicInfo.setSeverity (String.valueOf (emergencyinfo.getAccidentLevel ().getLevelcode ()));
						msgBasicInfo.setStartTime (DateUtils.formatDateTime (emergencyinfo.getStartTime ()));
						msgBasicInfo.setEndTime (DateUtils.formatDateTime (emergencyinfo.getEndTime ()));
						EBM.setMsgBasicInfo (msgBasicInfo);
						EBD_EBMBrdLog.MsgContent msgContent = new EBD_EBMBrdLog.MsgContent ();
						msgContent.setLanguageCode (emergencyinfo.getDisplayLanguage ().getShortname ());
						msgContent.setMsgTitle (emergencyinfo.getEmergencyname ());
						msgContent.setMsgDesc (emergencyinfo.getContent ());
						msgContent.setAreaCode (emergencyinfo.getAddresscode ());
						EBM.setMsgContent (msgContent);
						//EBD_EBMBrdLog.UnitInfo unitInfo = new EBD_EBMBrdLog.UnitInfo ();
						//EBD_EBMBrdLog.Unit unit = new EBD_EBMBrdLog.Unit ();
						EBD_EBMBrdLog.EBRPS ebrps = new EBD_EBMBrdLog.EBRPS ();
						ebrps.setEBRID (EEER.getEmerId ().toString ());
						/*unit.setPersonName("管理员");
						unit.setEBRPS (ebrps);
						unit.setUnitId ("001");
						unit.setUnitName (serverProperties.getUnitName ());
						unit.setPersonID ("001");
						unit.setPersonName (emergencyinfo.getCreateBy ());
						unitInfo.setUnit (unit);*/
						item.setEBM (EBM);
						//item.setUnitInfo (unitInfo);
						//播发状态代码 0：未处理 1：等待播发，指未到消息播发时间 2：播发中 3：播发成功 4：播发失败，包括播发全部失败、播发部分失败、未按要求播发等情况 5：播发取消
						item.setBrdStateCode ("3");
						item.setBrdStateDesc (emergencyinfo.getStatusDsc ());
						//以下内容为可选
						/*Coverage coverage = new Coverage();
						coverage.setAreaCode(emergencyinfo.getAreacode());
						coverage.setCoverageRate("99.1");
						coverage.setResBrdStat("1,1,1,1");
						item.setCoverage(coverage);
						ResBrdInfo resBrdInfo = new ResBrdInfo();
						List<ResBrdItem> resBrdItemList = new ArrayList<>();
						ResBrdItem resBrdItem = new ResBrdItem();
						EBRPS ebrps1 = new EBRPS();
						ebrps1.setEBRID(getEBD().getEBDID());
						resBrdItem.setEBRPS(ebrps1);
						resBrdItemList.add(resBrdItem);
						resBrdInfo.setResBrdItem(resBrdItemList);
						//resBrdInfo.setResBrdItem("");
						item.setResBrdInfo(resBrdInfo);*/
						EBD.EBMBrdLog.EBMBrdItem.add (item);
					}
				}
			});
		}
		return this;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
}
