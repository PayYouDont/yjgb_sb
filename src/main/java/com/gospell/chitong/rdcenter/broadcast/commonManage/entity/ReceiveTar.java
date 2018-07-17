package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * receive_tar
 * @author 
 */
@Data
public class ReceiveTar implements Serializable {
    /**
     * 对应tar包名字中的EBDT_后面id
     */
    private String id;

    private String resourceId;

    /**
     * 接收到tar包的时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private Integer status;

    private String auditUser;

    private Date auditTime;

    /**
     * tar包类型（1：接收包，2：发送包）
     */
    private Integer type;

    private String auditDepar;

    private String auditDesc;

    private String resourceCode;

    private String ebdType;

    private static final long serialVersionUID = 1L;
}