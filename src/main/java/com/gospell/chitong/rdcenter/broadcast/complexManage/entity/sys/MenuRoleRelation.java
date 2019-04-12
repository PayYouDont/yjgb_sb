package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
     * 查看权
     */
    private Boolean isView;

    /**
     * 修改权
     */
    private Boolean isModify;

    /**
     * 添加权
     */
    private Boolean isAdd;

    /**
     * 删除权
     */
    private Boolean isDelete;

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
    
    public Set<String> getPermsSet(String perms) {
    	Set<String> permsSet = new HashSet<>();
    	if(isView) {
    		permsSet.add(perms+":list");
    	}
    	if(isAdd) {
    		permsSet.add(perms+":add");
    	}
    	if(isModify) {
    		permsSet.add(perms+":edit");
    	}
    	if(isDelete) {
    		permsSet.add(perms+":delete");
    	}
    	return permsSet;
    }
}