package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.param;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * displaylanguage
 * @author 
 */
@Data
public class Displaylanguage implements Serializable {
    private Integer id;

    /**
     * 语言
     */
    private String language;

    /**
     * 序号
     */
    private String number;

    /**
     * 播发语言简写
     */
    private String shortname;

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