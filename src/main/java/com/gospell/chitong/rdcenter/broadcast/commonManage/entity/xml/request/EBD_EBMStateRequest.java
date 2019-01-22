/**   
* @Title: EBD_EBMStateRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:58:26 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.other.EBD_EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.EBD_EBM_EmerRelationService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;

/**
 * @ClassName: EBD_EBMStateRequest
 * @Description: TODO(播发状态请求)
 * @author peiyongdong
 * @date 2018年12月13日 下午5:58:26
 * 
 */
@lombok.Data
public class EBD_EBMStateRequest implements EBD{

	private EBD EBD;

	@lombok.Data
	@lombok.EqualsAndHashCode(callSuper=false)
	public static class EBD extends BaseEBD{
		private EBMStateRequest EBMStateRequest;
	}

	@lombok.Data
	public static class EBMStateRequest {
		private EBM EBM;
	}

	@lombok.Data
	public static class EBM {
		private String EBMID;
	}
	/** 
	 * <p>Title: creatResponseXML</p> 
	 * <p>Description: </p> 
	 * @return 
	 * @see com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD#creatResponseXML() 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年12月17日 上午9:35:16
	 */
	@Override
	public EBD_EBD creatResponse() {
		String ebmid = EBD.EBMStateRequest.EBM.EBMID;
		Map<String,Object> map = new HashMap<>();
		map.put("ebmId",ebmid);
		List<EBD_EBM_EmerRelation> list = ApplicationContextRegister.getBean(EBD_EBM_EmerRelationService.class).list(map);
		if(list == null) {
			return null;
		}
		if(list.size()>0) {
			/*EBD_EBM_EmerRelation eeer = list.get(0);
			String ebdId = eeer.getEbdId();*/
			
		}
		return null;
	}

}
