
package com.gospell.chitong.rdcenter.broadcast.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tass.exceptions.YJException;
import cn.tass.yingjgb.YingJGBCALLDLL;

/** 
* @ClassName: SignatureUtil 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年11月21日 上午11:52:20 
*  
*/
public class SignatureUtil {
	private static final Logger log = LoggerFactory.getLogger(SignatureUtil.class);
	//指令数据
	public static final int COMMAND_DATA = 0;
	//消息数据
	public static final int MESSAGE_DATA = 1;
	/**
	 * @Title: start 
	 * @Description: TODO(连接SJJ1313密码器设备)     设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月20日 上午10:40:44
	 */
	public static void start(int flagData) {
		try {
			YingJGBCALLDLL.openDevice(flagData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Title: stop 
	 * @Description: TODO(关闭密码设备)     设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月20日 上午10:40:33
	 */
	public static void stop() {
		try {
			YingJGBCALLDLL.closeDevice();
		} catch (YJException e) {
			log.error(e.getMessage(),e);
		}
	}
	/**
	 * @Title: signature 
	 * @Description: TODO(计算平台数据签名) 
	 * @param flag(0.指令数据1.消息数据)
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月20日 上午10:37:44
	 */
	public static String signature(int flag) {
		start(flag);
		//调用平台签名的方法
		byte[] inData = new byte[16];
		try {
			String sign = YingJGBCALLDLL.platformCalculateSignature(flag, inData);
			return sign;
		} catch (YJException e) {
			log.error(e.getMessage(),e);
			return e.getMessage();
		}
	}
	/**
	 * @Title: verifySignature 
	 * @Description: TODO(平台验证数据签名) 
	 * @param sign
	 * @param flag (0. 指令数据1.消息数据)
	 * @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年11月20日 上午10:35:54
	 */
	public static boolean verifySignature(String sign,int flag) {
		start(flag);
		byte[] inData = new byte[16];
		//返回验签结果
		try {
			
			boolean b = YingJGBCALLDLL.platformVerifySignature(flag, inData, sign);
			return b;
		} catch (YJException e) {
			log.error(e.getMessage(),e);
		}
		return false;
	}
	
	public static String getCertSN() {
		return "0001230000000001";
	}
}
