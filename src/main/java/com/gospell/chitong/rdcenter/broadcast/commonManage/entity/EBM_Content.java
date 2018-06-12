package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EBM_Content implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty	
	private String Language_code;
	@JsonProperty	
	private String Agency_name;
	@JsonProperty	
	private Long Code_character_set;
	@JsonProperty	
	private String Message_text;
	//此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，
	//如把trueName属性序列化为name，@JsonProperty("name")。不传参时序列化名为属性名
	@JsonProperty("Auxiliarys")
	private List<Auxiliary> Auxiliary = new ArrayList<Auxiliary>();
	@JsonIgnore
	public String getLanguage_code() {
		return Language_code;
	}
	@JsonIgnore
	public void setLanguage_code(String language_code) {
		Language_code = language_code;
	}
	@JsonIgnore
	public String getAgency_name() {
		return Agency_name;
	}
	@JsonIgnore
	public void setAgency_name(String agency_name) {
		Agency_name = agency_name;
	}
	@JsonIgnore
	public Long getCode_character_set() {
		return Code_character_set;
	}
	@JsonIgnore
	public void setCode_character_set(Long code_character_set) {
		Code_character_set = code_character_set;
	}
	@JsonIgnore
	public String getMessage_text() {
		return Message_text;
	}
	@JsonIgnore
	public void setMessage_text(String message_text) {
		Message_text = message_text;
	}
	@JsonIgnore
	public List<Auxiliary> getAuxiliary() {
		return Auxiliary;
	}
	@JsonIgnore
	public void setAuxiliary(List<Auxiliary> auxiliary) {
		Auxiliary = auxiliary;
	}
	
}
