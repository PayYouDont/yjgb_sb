package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

public interface AreaCodeChineseService {
	
	String getChinese(String areaCode);
	
	String getChinese(String code,int level);
	
	String getPcodeChinese(String codes);
}
