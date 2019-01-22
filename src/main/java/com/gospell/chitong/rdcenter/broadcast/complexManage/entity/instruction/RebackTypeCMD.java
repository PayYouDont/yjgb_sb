/**   
* @Title: RebackCMD.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午4:19:15 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: RebackCMD
 * @Description: TODO( 设置回传方式/回传地址指令 )
 * @author peiyongdong
 * @date 2019年1月21日 下午4:19:15
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RebackTypeCMD extends CMD {
	/*
	 *回传方式： 
	 * 	1：短信，地址为 11 个数字电话号码；
	 *  2：IP 地址和端口；
	 *  3：域名和端口号； 4～9：预留。
	 */
	private Byte rebackType;
	private Byte rebackAddressLength;
	/*
	 * reback_type=1 时，回传地址为 11 个数字电话号码，每个数字使用一个 ASCII 字符表示；
	 * reback_type=2 时，回传地址为 4字节 IP 地址+2 字节端口号，十六进制格式；
	 * reback_type=3 时，回传地址为域名和端口号，为 ASCII字符串格式，域名与端口号用“：”分开。
	 * 示例：www.chinaeb-lab.org：8080
	 */
	private String rebackAddress;
	private Byte terminalNumber;
	private Byte reserved = 1;
	private String resourceCode;
}
