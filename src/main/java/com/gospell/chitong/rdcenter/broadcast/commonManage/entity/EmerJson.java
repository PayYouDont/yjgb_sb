package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EmerJson implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty
    private EBM_Info EBM_Info = new EBM_Info();
	
	@JsonProperty
    private List<EBM_Content> EBM_Content = new ArrayList<EBM_Content>();
    @JsonIgnore
	public EBM_Info getEBM_Info() {
		return EBM_Info;
	}
	@JsonIgnore
	public void setEBM_Info(EBM_Info eBM_Info) {
		EBM_Info = eBM_Info;
	}
	@JsonIgnore
	public List<EBM_Content> getEBM_Content() {
		return EBM_Content;
	}
	@JsonIgnore
	public void setEBM_Content(List<EBM_Content> eBM_Content) {
		EBM_Content = eBM_Content;
	}
}
