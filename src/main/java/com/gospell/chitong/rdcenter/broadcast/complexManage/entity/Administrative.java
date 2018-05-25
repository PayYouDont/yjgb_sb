package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * administrative
 * @author 
 */
@Data
public class Administrative implements Serializable {
    private Integer id;

    /**
     * 区编号
     */
    private String code;

    /**
     * 区等级
     */
    private Integer codeLevel;

    /**
     * 中心纬度
     */
    private String latitude;

    /**
     * 中心经度
     */
    private String longitude;

    /**
     * 区名称
     */
    private String name;

    /**
     * 所属市级编号
     */
    private String parentCode;

    /**
     * 所有父级编号？
     */
    private String parentPath;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private static final long serialVersionUID = 1L;
}