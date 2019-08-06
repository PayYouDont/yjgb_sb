/**   
* @Title: BaseEBD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月17日 上午11:18:04 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;

/** 
* @ClassName: BaseEBD 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年12月17日 上午11:18:04 
*  
*/
@Data
public class BaseEBD{
	private String EBDVersion;
	private String EBDID;
	private String EBDType;
	private SRC SRC;
	private DEST DEST;
	private String EBDTime;
	private RelatedEBD RelatedEBD;
	@lombok.Data
	public static class RelatedEBD{
		private String EBDID;
	}
	@lombok.Data
	public static class SRC {
		private String EBRID;
		private String URL;
	}

	@lombok.Data
	public static class DEST {
		private String EBRID;
	}
	public void setEBDHeader() {
		ServerProperties prop = ApplicationContextRegister.getBean(ServerProperties.class);
		EBDVersion = prop.getServer_version();
		if(this.getClass().toString().indexOf("EBD_ConnectionCheck")!=-1){
			EBDID = EBDcodeUtil.getConnectionCheckID();
		}else{
			EBDID = EBDcodeUtil.getBaseEBDID();
		}
		SRC = new SRC();
		SRC.setEBRID(prop.getSRC_EBRID());
		DEST = new DEST();
		DEST.setEBRID(prop.getDEST_EBRID());
		EBDTime = DateUtils.getDateTime();
	}
}
