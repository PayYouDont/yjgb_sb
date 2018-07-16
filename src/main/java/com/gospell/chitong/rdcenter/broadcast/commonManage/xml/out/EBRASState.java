package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.ResponseXML;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 适配器状态
* @ClassName: EBRASState 
* @Description: TODO(  适配器状态   ) 
* @author peiyongdong
* @date 2018年7月9日 上午9:33:35 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASState extends BaseXML implements ResponseXML{

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
