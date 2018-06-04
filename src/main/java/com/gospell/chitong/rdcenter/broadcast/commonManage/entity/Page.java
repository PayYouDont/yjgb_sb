package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Page {

	// 用户输入的分页条件
	private int pageIndex = 1; // 当前页
	private int pageSize=10; // 每页最大行数

	// 用于实现分页SQL的条件，是根据用户输入条件计算而来的
	private int begin;
	private int end;
	//参数map 可以在map中设置查询条件
	private Map<String,Object> map;
	
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
	
	public Map<String,Object> getMap() {
		map = new HashMap<String, Object>();
		map.put("pageSize", this.getPageSize());
		map.put("begin", this.getBegin());
		return map;
	}
}
