package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * accidenttype
 * @author 
 */
@Data
public class Accidenttype implements Serializable {
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 序号
     */
    private Integer number;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updataBy;

    private static final long serialVersionUID = 1L;
}