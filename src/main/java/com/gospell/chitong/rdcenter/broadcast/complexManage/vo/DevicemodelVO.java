package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import java.util.Date;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Devicemodel;

import lombok.Data;

@Data
public class DevicemodelVO {
	private Integer id;
    private String devmodel;
    private String devmodeldescription;
    private Integer devicetypeId;
    private String devmodelcode;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String deviceModelParamIds;
    public Devicemodel getDevicemodel() {
    	Devicemodel model = new Devicemodel();
    	model.setId(getId());
    	model.setDevmodel(getDevmodel());
    	model.setDevmodeldescription(getDevmodeldescription());
    	model.setDevicetypeId(getDevicetypeId());
    	model.setDevmodelcode(getDevmodelcode());
    	model.setCreateTime(getCreateTime());
    	model.setCreateBy(getCreateBy());
    	model.setUpdateTime(getUpdateTime());
    	model.setUpdateBy(getUpdateBy());
    	return model;
    }
}
