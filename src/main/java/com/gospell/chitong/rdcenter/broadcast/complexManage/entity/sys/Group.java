package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName Group
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 10:52
 **/
@Data
@Entity
@Table(name ="sys_group")
@EqualsAndHashCode(callSuper = false)
public class Group extends BaseEntity<Group> {
    private String name;
    private String description;
}
