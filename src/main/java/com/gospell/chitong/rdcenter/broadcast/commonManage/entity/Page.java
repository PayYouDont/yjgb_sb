package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import lombok.Data;

@Data
public class Page {

	// 用户输入的分页条件
	private int pageIndex = 1; // 当前页
	private int pageSize; // 每页最大行数

	// 用于实现分页SQL的条件，是根据用户输入条件计算而来的
	private int begin;
	private int end;

	// 根据总行数计算总页数，然后将总页数输出给页面
	private int totalPage;
	
	private String areaCode;
	//排序字段
	private String sortStr;
	//排序方式:DESC ASC
	private String order;
	//搜索内容
	private String search = "";

	public int getBegin() {
		// 在mapper.xml使用begin属性时，对其进行计算
		begin = (pageIndex - 1) * pageSize;
		return begin;
	}

	public int getEnd() {
		// 在mapper.xml使用end属性时，对其进行计算
		end = pageIndex * pageSize + 1;
		return end;
	}
}
