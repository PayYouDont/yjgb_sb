/**   
* @Title: EBD_EBMStateRequest.java 
* @Package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2018年12月13日 下午5:58:26 
*/
package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.resolve.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity.Emergencyinfo;
import com.gospell.chitong.rdcenter.broadcast.broadcastMange.service.EmergencyInfoService;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.BaseEBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base.EBD;
import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.model.EBD_EBMStateResponse;
import com.gospell.chitong.rdcenter.broadcast.commonManage.service.EBD_EBM_EmerRelationService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.XMLUtil;

/**
 * @ClassName: EBD_EBMStateRequest
 * @Description: TODO(播发状态请求,应急广播消息播发状态查询文件)
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
	@Override
	public EBD_EBMStateResponse creatResponse() {
		String ebmid = EBD.EBMStateRequest.EBM.EBMID;
		Map<String,Object> map = new HashMap<>();
		map.put("ebmId",ebmid);
		List<EBD_EBM_EmerRelation> list = ApplicationContextRegister.getBean(EBD_EBM_EmerRelationService.class).list(map);
		if(list == null) {
			return null;
		}
        EmergencyInfoService emerService = ApplicationContextRegister.getBean (EmergencyInfoService.class);
		if(list.size()>0) {
			Integer emerId = list.get(0).getEmerId ();
			Emergencyinfo info = emerService.selectById (emerId);
			if(info!=null){
				return new EBD_EBMStateResponse(info,getEBD().getEBDID());
			}
		}
		return null;
	}

}
