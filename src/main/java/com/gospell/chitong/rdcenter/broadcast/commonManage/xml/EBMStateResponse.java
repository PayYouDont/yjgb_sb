package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 播发状态回执
* @ClassName: EBMStateResponse 
* @Description: TODO(  播发状态回执   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:20:31 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBMStateResponse extends BaseXML implements ResponseXML{

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
