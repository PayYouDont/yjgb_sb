package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * displaymethod
 * @author 
 */
@Data
public class Displaymethod implements Serializable {
    private Integer id;

    /**
     * 播发方式
     */
    private String method;

    /**
     * 播发方式编码
     */
    private String code;

    /**
     * 序号
     */
    private String number;

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

    private static final long serialVersionUID = 1L;
}