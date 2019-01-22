package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_reback_period
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDRebackPeriod extends CMD {
	/**
	 * 回传周期统一换算为秒，1 天=86400 秒，则此 处为 86400
	 * 
	 */
	private Integer rebackPeriod;

	/**
	 * 需要配置的终端资源编码的个数
	 */
	private Integer terminalNumber;

	private Integer reserved;

	private String resourceCode;

	private static final long serialVersionUID = 1L;
}