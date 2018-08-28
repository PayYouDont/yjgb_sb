package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * user_log
 * @author 
 */
@Data
public class UserLog implements Serializable {
	
    private Integer id;

    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 客户端ip
     */
    private String clientip;

    /**
     * 模块
     */
    private String urlmodule;

    /**
     * 功能
     */
    private String urlfunction;

    /**
     * 创建(访问)时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 用户ID
     */
    private Integer userId;
    
    /**
     * 用户角色名
     */
    private String roleName;

    /**
     * 用户角色ID
     */
    private String roleId;

    /**
     * 业务数据
     */
    private String des;

    private static final long serialVersionUID = 1L;
}