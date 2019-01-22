/**   
* @Title: RebackPeriodCMD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:22:45 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: RebackPeriodCMD 
* @Description: TODO(  设置回传周期指令   ) 
* @author peiyongdong
* @date 2019年1月21日 下午4:22:45 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class RebackPeriodCMD extends CMD{
	/*
	 * 回传周期统一换算为秒，1 天=86400 秒，则此处为 86400
	 */
	private Integer rebackPeriod;
	private Byte terminalNumber;
	private Byte reserved = 1;
	private String resourceCode;
}
