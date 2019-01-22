/**   
* @Title: TimeCmd.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午3:54:25 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: TimeCmd 
* @Description: TODO(  时钟校准指令  ) 
* @author peiyongdong
* @date 2019年1月21日 下午3:54:25 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class TimeCMD extends CMD{
	private Short wYear;
	private Byte iMonth;
	private Byte iDay;
	private Byte iHour;
	private Byte iMinute;
	private Byte iSecond;
}
