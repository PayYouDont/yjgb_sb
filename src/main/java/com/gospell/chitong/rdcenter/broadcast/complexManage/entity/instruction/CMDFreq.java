package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_freq
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDFreq extends CMD {

	/**
	 * 主频频率（kHz）
	 */
	private Integer freq;

	/**
	 * 符号率(kBPS)
	 * 
	 */
	private Integer symbolRate;

	/**
	 * 0x00：未定义； 
	 * 0X01：QAM16； 
	 * 0X02：QAM32; 
	 * 0X03：QAM64; 
	 * 0X04：QAM128; 
	 * 0X05：QAM256;
	 * 0X060XFF：预留；
	 */
	private Integer constellationMapping;

	/**
	 * 需要设置锁定频率的终端资源编码个数
	 */
	private Integer terminalNumber;

	private Integer reserved;

	private String resourceAddress;

	private static final long serialVersionUID = 1L;
}