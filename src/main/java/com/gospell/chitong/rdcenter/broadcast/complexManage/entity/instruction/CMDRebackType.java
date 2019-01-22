package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_reback_type
 * 
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDRebackType extends CMD {

	/**
	 * 回传方式： 1：短信，地址为 11 个数字电话号码； 2：IP 地址和端口； 3：域名和端口号； 4～9：预留。
	 * 
	 */
	private Integer rebackType;

	/**
	 * 回传地址参数长度
	 */
	private Integer rebackAddressLength;

	/**
	 * reback_type=1 时，回传地址为 11 个数字电 话号码，每个数字使用一个 ASCII 字符表示； reback_type=2 时，回传地址为
	 * 4 字节 IP 地 址+2 字节端口号，十六进制格式； reback_type=3 时，回传地址为域名和端口 号，为 ASCII
	 * 字符串格式，域名与端口号用 “：”分开。 示例： www.chinaeb-lab.org：8080
	 */
	private String rebackAddress;

	/**
	 * 需要配置的终端资源编码的个数
	 */
	private Integer terminalNumber;

	private Integer reserved;

	private String resourceCode;

	private static final long serialVersionUID = 1L;
}