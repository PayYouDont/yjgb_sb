/**   
* @Title: VolumeCMD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:27:25 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: VolumeCMD 
* @Description: TODO(  设置默认音量指令  ) 
* @author peiyongdong
* @date 2019年1月21日 下午4:27:25 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class VolumeCMD extends CMD{
	/*
	 *音量按百分比形式标识，其中：
	 * 	0x00：静音
	 * 	0x01～0x64：对应音量 1%～100%
	 * 	其他取值无意义
	 */
	private Byte volume;
	/*
	 * 需要配置的终端资源编码的个数
	 */
	private Byte terminal_number;
	private Byte reserved;
	private String resource_code;
}
