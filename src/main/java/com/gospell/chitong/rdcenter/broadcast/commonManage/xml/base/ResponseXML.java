package com.gospell.chitong.rdcenter.broadcast.commonManage.xml.base;

public interface ResponseXML{
	
	BaseXML createFullEntity();
	
	BaseXML createIncrementalEntity();
	
	default String getResultCode() {
		return "";
	};
	
	default String getResultDesc() {
		return "";
	};
}
