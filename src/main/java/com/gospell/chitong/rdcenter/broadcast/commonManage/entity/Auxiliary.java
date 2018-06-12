package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Auxiliary implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty
    private String Auxiliary_data;
	@JsonProperty
	private Long Auxiliary_data_type;
	@JsonIgnore
	public String getAuxiliary_data() {
		return Auxiliary_data;
	}
	@JsonIgnore
	public void setAuxiliary_data(String auxiliary_data) {
		Auxiliary_data = auxiliary_data;
	}
	@JsonIgnore
	public Long getAuxiliary_data_type() {
		return Auxiliary_data_type;
	}
	@JsonIgnore
	public void setAuxiliary_data_type(Long auxiliary_data_type) {
		Auxiliary_data_type = auxiliary_data_type;
	}

}
