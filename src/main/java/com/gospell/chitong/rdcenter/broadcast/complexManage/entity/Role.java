package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    /**
     * 权限组名称
     */
    private String name;

    /**
     * 权限组描述
     */
    private String descript;

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

    /**
     * 是否有效
     */
    private Integer state;

    /**
     * 角色在系统中的等级 0:不能删除和修改（一般指超级管理） 
     */
    private String level;

    private static final long serialVersionUID = 1L;
}