package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户管理区域编号
     */
    private String areaCode;

    /**
     * 用户区域级别
     */
    private String areaCodeLevel;

    /**
     * 管理区域名称
     */
    private String areaCodeName;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")//页面写入数据库时格式化
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
    
    private List<Role> roleList;
}