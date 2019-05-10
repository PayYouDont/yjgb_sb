/**   
* @Title: EBD_EBRBSInfo.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:55:17 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.info;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBDResponse;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: EBD_EBRBSInfo
 * @Description: TODO(传输覆盖播出设备信息)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:55:17
 * 
 */
@lombok.Data
public class EBD_EBRBSInfo implements EBDResponse {

	private EBD EBD;

	@lombok.Data
	@EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBRBSInfo EBRBSInfo;
	}

	@lombok.Data
	public static class EBRBSInfo {
		private Params Params;
		private EBRBS EBRBS;
	}

	@lombok.Data
	public static class Params {
		private String RptStartTime;
		private String RptEndTime;
		private String RptType;
	}

	@lombok.Data
	public static class EBRBS {
		private String RptTime;
		private String RptType;
		private RelatedEBRPS RelatedEBRPS;
		private RelatedEBRST RelatedEBRST;
		private RelatedEBRAS RelatedEBRAS;
		private String EBRID;
		private String EBRName;
		private String Longitude;
		private String Latitude;
		private String URL;
		private String Square;
		private String AreaCode;
		private String Population;
		private String LanguageCode;
		private String EquipRoom;
		private RadioParams RadioParams;
		private TVParams TVParams;
	}

	@lombok.Data
	public static class RelatedEBRPS {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRST {
		private String EBRID;
	}

	@lombok.Data
	public static class RelatedEBRAS {
		private String EBRID;
	}

	@lombok.Data
	public static class RadioParams {
		private String ChannelName;
		private String Freq;
		private String Power;
		private String Backup;
		private String AutoSwitch;
		private String RemoteControl;
	}

	@lombok.Data
	public static class TVParams {
		private String ChannelName;
		private String Freq;
		private String ProgramNum;
		private String ChannelNum;
	}

	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponseXML()
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:36:05
	 */
	@Override
	public EBD_EBRBSInfo creatResponse() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public EBDResponse createFullResponse() {
        return null;
    }

    @Override
    public EBDResponse createIncrementalResponse() {
        return null;
    }
}
