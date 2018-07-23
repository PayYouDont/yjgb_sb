
package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base.BaseXML;
import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRASState;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBRASStateVO 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月23日 上午9:03:58 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRASStateVO extends BaseXML{
	
	private String EBRAS_RptTime;
	private String EBRAS_EBRID;
	private String EBRAS_StateCode;
	private String EBRAS_StateDesc;
	public Map<String,Object> getMap(){
		Map<String,Object> EBRAS = new LinkedHashMap<String, Object>();
		EBRAS.put("RptTime", getEBRAS_RptTime());
		EBRAS.put("EBRID", getEBRAS_EBRID());
		EBRAS.put("StateCode", getEBRAS_StateCode());
		EBRAS.put("StateDesc", getEBRAS_StateDesc());
		return EBRAS;
	}
	
	public static List<EBRASStateVO> getList(List<Deviceinfo> deviceinfos,EBRASState state) {
		List<EBRASStateVO> list = new ArrayList<>();
		for (Deviceinfo info : deviceinfos) {
			EBRASStateVO vo = new EBRASStateVO();
			vo.setEBRAS_RptTime(DateUtils.getDateTime());
			vo.setEBRAS_EBRID(EBDcodeUtil.getEBRID(info, "0301010201"));
			vo.setEBRAS_StateCode(info.getOnwork());
			vo.setEBRAS_StateDesc("1".equals(info.getOnwork())?"工作":"待机");
			list.add(vo);
		}
		return list;
	}
}
