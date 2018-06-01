package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import lombok.Data;

@Data
public class Page {

	// 用户输入的分页条件
	private int currentPage = 1; // 当前页
	private int pageSize = 15; // 每页最大行数

	// 用于实现分页SQL的条件，是根据用户输入条件计算而来的
	private int begin;
	private int end;

	// 自动计算出的总行数
	private int rows;
	// 根据总行数计算总页数，然后将总页数输出给页面
	private int totalPage;

	private String areaCode;
	//排序字段
	private String sortStr;
	//排序方式:DESC ASC
	private String order;
	
	public int getTotalPage() {
		// 根据总行数，计算总页数
		if (rows % pageSize == 0) {
			totalPage = rows / pageSize;
		} else {
			totalPage = rows / pageSize + 1;
		}
		return totalPage;
	}

	public int getBegin() {
		// 在mapper.xml使用begin属性时，对其进行计算
		begin = (currentPage - 1) * pageSize;
		return begin;
	}

	public int getEnd() {
		// 在mapper.xml使用end属性时，对其进行计算
		end = currentPage * pageSize + 1;
		return end;
	}
}
