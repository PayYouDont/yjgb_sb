package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_volume
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDVolume extends CMD {
	private Integer id;

	/**
	 * 指令名称
	 */
	private String name;

	/**
	 * 指令标识符
	 */
	private Integer tag;

	/**
	 * 指令长度,单位为字节
	 */
	private Integer length;

	/**
	 * 音量按百分比形式标识，其中： 
	 * 	0x00：静音 
	 * 	0x01～0x64：对应音量 1%～100% 
	 *  其他取值无意义
	 */
	private Integer volume;

	/**
	 * 需要配置的终端资源编码的个数
	 */
	private Integer terminalNumber;

	private Integer reserved;

	private String resourceCode;

	private static final long serialVersionUID = 1L;
}