package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Page {

	// 用户输入的分页条件
	private int pageIndex = 1; // 当前页
	private int pageSize=10; // 每页最大行数
	public int getBegin() {
		// 在mapper.xml使用begin属性时，对其进行计算
		return (pageIndex - 1) * pageSize;
	}

	public int getEnd() {
		// 在mapper.xml使用end属性时，对其进行计算
		return pageIndex * pageSize + 1;
	}
	
	public Map<String,Object> getMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pageSize", this.getPageSize());
		map.put("begin", this.getBegin());
		return map;
	}
}
