/**   
* @Title: ParameterCMD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:30:21 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: ParameterCMD 
* @Description: TODO(  状态/参数查询指令   ) 
* @author peiyongdong
* @date 2019年1月21日 下午4:30:21 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class ParameterCMD extends CMD{
	/*
	 * 查询参数个数
	 */
	private Byte parameterNumber;
	private String parameterTag;
	/*
	 * 需要配置的终端资源编码的个
	 */
	private Byte terminalNumber;
	private Byte reserved = 1;
	private String resourceCode;
}
