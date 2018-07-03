package com.gospell.chitong.rdcenter.broadcast.util;

public class EBDcodeUtil {
	
	public static int EBDID = 0;
	
	public static String getEBDIDCode() {
		EBDID++;
		return StringUtil.patch("0", 8, EBDID);
	}
	public static String getConnectionCheckCode() {
		EBDID++;
		return StringUtil.patch("0", 16, EBDID);
	}
}
