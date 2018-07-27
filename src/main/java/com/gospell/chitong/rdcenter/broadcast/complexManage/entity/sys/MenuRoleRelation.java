package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * menu_role_relation
 * @author 
 */
@Data
public class MenuRoleRelation implements Serializable {
    private Integer id;

    /**
     * 菜单id
     */
    private Integer menuId;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 创建授权时间
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

    private static final long serialVersionUID = 1L;
}