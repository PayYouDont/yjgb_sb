package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EBM_Info implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty
    private Integer EBM_innerIndex;
	@JsonProperty
	private Integer EBM_class;
	@JsonProperty
	private String EBM_end_time;
	@JsonProperty
	private String EBM_start_time;  
	@JsonProperty
	private String EBM_type; 
	@JsonProperty
	private Integer EBM_level;
	@JsonProperty
	private Integer EBM_vocie;
	@JsonProperty
	private Integer Detail_ProgramNum;
	@JsonProperty
	private List<String> EBM_resource_code = new ArrayList<String>();
	
	@JsonIgnore
	public Integer getEBM_innerIndex() {
		return EBM_innerIndex;
	}
	@JsonIgnore
	public void setEBM_innerIndex(Integer eBM_innerIndex) {
		EBM_innerIndex = eBM_innerIndex;
	}
	@JsonIgnore
	public Integer getEBM_class() {
		return EBM_class;
	}
	@JsonIgnore
	public void setEBM_class(Integer eBM_class) {
		EBM_class = eBM_class;
	}
	@JsonIgnore
	public String getEBM_end_time() {
		return EBM_end_time;
	}
	@JsonIgnore
	public void setEBM_end_time(String eBM_end_time) {
		EBM_end_time = eBM_end_time;
	}
	@JsonIgnore
	public String getEBM_start_time() {
		return EBM_start_time;
	}
	@JsonIgnore
	public void setEBM_start_time(String eBM_start_time) {
		EBM_start_time = eBM_start_time;
	}
	@JsonIgnore
	public String getEBM_type() {
		return EBM_type;
	}
	@JsonIgnore
	public void setEBM_type(String eBM_type) {
		EBM_type = eBM_type;
	}
	@JsonIgnore
	public Integer getEBM_level() {
		return EBM_level;
	}
	@JsonIgnore
	public void setEBM_level(Integer eBM_level) {
		EBM_level = eBM_level;
	}
	@JsonIgnore
	public Integer getEBM_vocie() {
		return EBM_vocie;
	}
	@JsonIgnore
	public void setEBM_vocie(Integer eBM_vocie) {
		EBM_vocie = eBM_vocie;
	}
	@JsonIgnore
	public Integer getDetail_ProgramNum() {
		return Detail_ProgramNum;
	}
	@JsonIgnore
	public void setDetail_ProgramNum(Integer detail_ProgramNum) {
		Detail_ProgramNum = detail_ProgramNum;
	}
	@JsonIgnore
	public List<String> getEBM_resource_code() {
		return EBM_resource_code;
	}
	@JsonIgnore
	public void setEBM_resource_code(List<String> eBM_resource_code) {
		EBM_resource_code = eBM_resource_code;
	}
}
