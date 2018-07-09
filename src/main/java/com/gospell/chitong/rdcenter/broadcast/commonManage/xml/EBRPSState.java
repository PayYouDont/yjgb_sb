package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 平台状态信息
* @ClassName: EBRPSState 
* @Description: TODO(  平台状态信息   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:26:17 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRPSState extends BaseXML implements ResponseXML{

	@Override
	public BaseXML createFullEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseXML createIncrementalEntity() {
		// TODO Auto-generated method stub
		return null;
	}


}
