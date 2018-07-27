package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * devicetype
 * @author 
 */
@Data
public class Devicetype implements Serializable {
    private Integer id;

    /**
     * 设备类型
     */
    private String devtype;

    /**
     * 设备类型描述
     */
    private String devtypedescription;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    /**
     * 设备类型code
     */
    private String devtypecode;

    private static final long serialVersionUID = 1L;
}