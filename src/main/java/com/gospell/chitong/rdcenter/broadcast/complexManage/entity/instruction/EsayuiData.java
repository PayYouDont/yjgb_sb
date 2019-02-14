/**   
* @Title: EsayuiData.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年2月14日 上午9:18:47 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.util.ArrayList;

import lombok.Data;

/** 
* @ClassName: EsayuiData 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年2月14日 上午9:18:47 
*  
*/
@Data
public class EsayuiData {

	private int total;
	private ArrayList<Rows> rows;

	@Data
	public static class Rows {
		private String attrType;
		private String attrName;
		private String attrValue;
		private String explain;
	}


}
