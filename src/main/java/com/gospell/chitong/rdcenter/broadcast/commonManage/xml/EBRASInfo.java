package com.gospell.chitong.rdcenter.broadcast.commonManage.xml;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 适配器信息
* @ClassName: EBRASInfo 
* @Description: TODO(  适配器信息   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:31:55 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASInfo extends BaseXML implements ResponseXML{

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
