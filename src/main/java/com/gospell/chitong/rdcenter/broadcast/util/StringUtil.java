package com.gospell.chitong.rdcenter.broadcast.util;

public class StringUtil {

	/**
	 * 数字补码
	 * @Title: patch 
	 * @Description: TODO(数字补码) 
	 * @param @param patcher(补码符)
	 * @param @param length（补码长度）
	 * @param @param code（待补数字）
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年7月3日 上午10:44:13
	 */
	public static String patch(String patcher,Integer length,Integer code) {
		if(patcher.equals("")||patcher==null) {
			return code.toString();
		}
		if(length<=0) {
			return code.toString();
		}
		int index = 1;
		Integer code2 = code;
		while(code2/10>=1) {
			index++;
			code2 = code2/10;
		}
		if(index>=length) {
			return code.toString();
		}
		String patchStr = "";
		for(int i=0;i<length;i++) {
			patchStr += patcher;
		}
		patchStr += code;
		patchStr = patchStr.substring(index,patchStr.length());
		return patchStr;
	}
	
	public static void main(String[] args) {
		System.out.println("445103100000;445103101000;445103104000;445103105000;445103106000;445103107000;445103108000;445103109000;445103110000;445103114000;445103115000;445103116000;445103117000;445103120000;445103121000;445103400000;445103404000;445103405000;445103450000;".length());
		//445103100000;445103101000;445103104000;445103105000;445103106000;445103107000;445103108000;445103109000;445103110000;445103114000;445103115000;445103116000;445103117000;445103120000;445103121000;445103400000;445103404000;445103405000;445103450000;
	}
}
