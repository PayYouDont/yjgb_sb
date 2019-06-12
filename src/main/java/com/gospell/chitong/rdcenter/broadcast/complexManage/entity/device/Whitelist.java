package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName Whitelist
 * @Description TODO
 * @Author pay
 * @DATE 2019/6/12 15:23
 **/
@Data
@Entity
@Table(name="whitelist")
@EqualsAndHashCode(callSuper = false)
public class Whitelist extends BaseEntity {
    private String phoneNumber;
    private String userName;
    //许可类型 1:代表短信;2:代表电话;3 代表短信和电话
    private Integer permissionType;
    //许可类型名称
    private String permissionName;
    //授权区域码
    private String permissionAreaCode;

    public Whitelist(String phoneNumber, String userName, Integer permissionType, String permissionName, String permissionAreaCode) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.permissionType = permissionType;
        this.permissionName = permissionName;
        this.permissionAreaCode = permissionAreaCode;
    }

    public Whitelist() {
    }
}
