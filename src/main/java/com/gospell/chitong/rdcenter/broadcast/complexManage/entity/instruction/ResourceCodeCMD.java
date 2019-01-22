/**   
* @Title: ResourceCodeCmd.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:08:27 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: ResourceCodeCmd 
* @Description: TODO(  资源编码设置指令   ) 
* @author peiyongdong
* @date 2019年1月21日 下午4:08:27 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceCodeCMD extends CMD{
	/*
	 * 物理地址长度
	 */
	private Short terminalAddressLength;
	/*
	 * 物理地址 n=terminal_address_length
	 */
	private String terminalAddress;
	private Byte reserved = 1;
	private String resourceCode;
}
