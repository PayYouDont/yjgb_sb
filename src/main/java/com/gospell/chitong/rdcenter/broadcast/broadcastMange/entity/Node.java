package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * node
 * @author 
 */
@Data
public class Node implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 节点地址
     */
    private String url;

    /**
     * 节点链接状态(0:连接失败,1:连接成功)
     */
    private Integer linkStatus;

    /**
     * 节点状态(0:停用，1:启用)
     */
    private Integer nodeStatus;

    /**
     * 发送方EBRID
     */
    private String srcEbrid;

    /**
     * 接收方EBRID
     */
    private String destEbrid;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 节点ebdId
     */
    private String ebdId;

    /**
     * 心跳频率
     */
    private Integer heartRate;
    
    private static final long serialVersionUID = 1L;
}