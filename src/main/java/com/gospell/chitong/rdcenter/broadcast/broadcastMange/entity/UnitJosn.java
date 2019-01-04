package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class UnitJosn {
	@JsonProperty	
	private String name;//名
	@JsonProperty	
	private String level;//平台覆盖区域地址编码等级（所属哪一级，省 市 县。。。）
	@JsonProperty	
	private String code;//编码
	@JsonProperty	
	private String ip;//ip
	@JsonProperty	
	private String address;//地址
	@JsonProperty	
	private String longitude;//中心经度
	@JsonProperty	
	private String latitude;// 中心纬度
	@JsonProperty	
	private String extra;//其他
}
