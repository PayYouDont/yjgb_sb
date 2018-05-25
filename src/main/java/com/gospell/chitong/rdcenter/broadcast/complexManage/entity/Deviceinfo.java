package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * deviceinfo
 * @author 
 */
@Data
public class Deviceinfo implements Serializable {
    private Integer id;

    /**
     * 设备名称
     */
    private String devname;

    /**
     * 设备序列号
     */
    private String devdsn;

    /**
     * 设备寻址号
     */
    private String devcode;

    /**
     * 设备地址号
     */
    private String devhexcode;

    /**
     * 设备地址行政编号
     */
    private String devaddresscode;

    /**
     * 设备地址名称
     */
    private String devaddress;

    /**
     * 设备状态(8位二进制格式，如:00000001)
     */
    private String status;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 当前应急消息挂载的应急消息的id
     */
    private Integer messageid;

    /**
     * 设备型号id
     */
    private Integer devicemodelId;

    private Integer deviceid;

    /**
     * 状态描述
     */
    private String statusscript;

    /**
     * 发现序列号时间
     */
    private Date timefind;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    private String parentpath;

    private static final long serialVersionUID = 1L;
}