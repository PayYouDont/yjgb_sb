package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * device_log
 * @author 
 */
@Data
public class DeviceLog implements Serializable {
    private Integer id;

    /**
     * 日志产生时间
     */
    private Date createTime;

    /**
     * 设备序列号
     */
    private String devicedsn;

    /**
     * 设备序名称
     */
    private String devicename;

    /**
     * 原始状态
     */
    private String prestatus;

    /**
     * 设备状态
     */
    private String status;

    private static final long serialVersionUID = 1L;
}