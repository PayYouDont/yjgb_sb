package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeviceJson implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty	
	private String DevDsn;
	@JsonProperty	
	private String DevHexcode;
	@JsonProperty	
	private String DevCode;
	@JsonProperty
	Map<String, Object> paramMap = new HashMap<String, Object>();
}
