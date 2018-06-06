package com.gospell.chitong.rdcenter.broadcast.util;

import java.util.Calendar;

public class EBMessageUtil {
	private static int pureMsgId = 0;

	public static String generateMessageId(String sendTime) {
		String msgid = String.format("%s%02d", sendTime, pureMsgId++);
		if (pureMsgId == 15)
			pureMsgId = 0;
		return msgid;
	}

	public static String generateSendtime() {

		Calendar nowtime = Calendar.getInstance();

		int Y = nowtime.get(Calendar.YEAR) - 1900;
		int M = nowtime.get(Calendar.MONTH) + 1;
		int D = nowtime.get(Calendar.DAY_OF_MONTH);
		int H = nowtime.get(Calendar.HOUR_OF_DAY);
		int Mi = nowtime.get(Calendar.MINUTE);
		int S = nowtime.get(Calendar.SECOND);
		
		int L;
		int MJD;
		if (M == 1 || M == 2)
			L = 1;
		else
			L = 0;

		MJD = 14956 + D + (int) ((Y - L) * 365.25)
				+ (int) ((M + 1 + L * 12) * 30.6001);

		return String.format("%05d%02d%02d%02d", MJD, H, Mi, S);
	}
	public static void main(String [] args){
		System.out.println(generateSendtime());
	}
}
