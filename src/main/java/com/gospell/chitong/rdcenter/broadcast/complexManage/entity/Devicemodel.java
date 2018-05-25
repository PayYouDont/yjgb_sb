package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * devicemodel
 * @author 
 */
@Data
public class Devicemodel implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 设备型号名

     */
    private String devmodel;

    /**
     * 设备型号描述
     */
    private String devmodeldescription;

    /**
     * 设备类型id
     */
    private Integer devicetypeId;

    /**
     * 设备型号编码
     */
    private String devmodelcode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    private static final long serialVersionUID = 1L;
}