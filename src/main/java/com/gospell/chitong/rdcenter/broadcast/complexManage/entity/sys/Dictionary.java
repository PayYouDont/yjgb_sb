package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @ClassName Dictionary
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 10:34
 **/
@Data
@Entity
@Table(name="sys_dictionary")
@EqualsAndHashCode(callSuper = false)
public class Dictionary extends BaseEntity<Dictionary> {
    private String field;
    private String fieldKey;
    private String fieldValue;
    @OneToOne(cascade = CascadeType.PERSIST)
    //referencedColumnName：参考列名,默认的情况下是列表的主键
    //nullable=是否可以为空，
    //insertable：是否可以插入，
    //updatable：是否可以更新
    // columnDefinition=列定义，
    //foreignKey=外键
    @JoinColumn(name = "group_id",referencedColumnName="id")
    private Group group;
}
