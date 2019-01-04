/**   
* @Title: EBD_EBMBrdLog.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:32:23 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBMBrdLog
 * @Description: TODO(播发记录)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:32:23
 * 
 */
@lombok.Data
public class EBD_EBMBrdLog implements EBD{

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private String EBDTime;
		private EBMBrdLog EBMBrdLog;
	}
	
	@lombok.Data
	public static class EBMBrdLog {
		private Params Params;
		private EBMBrdItem EBMBrdItem;
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
		private String UnitID;
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
		private String ResBrdItem;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:02
	 */
	@Override
	public EBD_EBMBrdLog creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}
}
