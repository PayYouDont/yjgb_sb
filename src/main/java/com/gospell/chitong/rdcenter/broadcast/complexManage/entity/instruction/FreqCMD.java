/**   
* @Title: FreqCMD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:15:31 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: FreqCMD 
* @Description: TODO(  锁定频率设置指令  ) 
* @author peiyongdong
* @date 2019年1月21日 下午4:15:31 
*  
*/
@Data
@EqualsAndHashCode(callSuper=false)
public class FreqCMD extends CMD{
	/*
	 * 主频频率（kHz）
	 */
	private Integer freq;
	/*
	 * 符号率(kBPS)
	 */
	private Integer symbolRate;
	/*
	 *0x00：未定义；
	 *	0X01：QAM16；
	 *	0X02：QAM32;
	 *	0X03：QAM64;
	 *	0X04：QAM128;
	 *	0X05：QAM256;
	 *	0X06-0XFF：预留；
	 */
	private Short constellationMapping;
	/*
	 * 需要设置锁定频率的终端资源编码个数
	 */
	private Short terminalNumber;
	private Byte reserved = 1;
	private String resourceAddress;
}
