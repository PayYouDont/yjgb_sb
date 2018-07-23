
package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.out.EBRDTState;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.Deviceinfo;
import com.gospell.chitong.rdcenter.broadcast.util.DateUtils;
import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: EBRDTStateVO 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年7月23日 上午9:23:09 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class EBRDTStateVO {
	private String EBRDT_RptTime;
	private String EBRDT_EBRID;
	private String EBRDT_StateCode;
	private String EBRDT_StateDesc;
	
	public Map<String,Object> getMap(){
		Map<String,Object> EBRDT = new LinkedHashMap<String, Object>();
		EBRDT.put("RptTime", getEBRDT_RptTime());
		EBRDT.put("EBRID", getEBRDT_EBRID());
		EBRDT.put("StateCode", getEBRDT_StateCode());
		EBRDT.put("StateDesc", getEBRDT_StateDesc());
		return EBRDT;
	}
	public static List<EBRDTStateVO> getList(List<Deviceinfo> deviceinfos,EBRDTState EBRDTState) {
		List<EBRDTStateVO> list = new ArrayList<>();
		for (Deviceinfo info : deviceinfos) {
			EBRDTStateVO vo = new EBRDTStateVO();
			vo.setEBRDT_RptTime(DateUtils.getDateTime());
			//6 341523100201 0314040401
			vo.setEBRDT_EBRID(EBDcodeUtil.getEBRID(info, "0314040401"));
			vo.setEBRDT_StateCode(info.getOnwork());
			vo.setEBRDT_StateDesc("1".equals(info.getOnwork())?"工作":"待机");
			list.add(vo);
		}
		return list;
	}
}
