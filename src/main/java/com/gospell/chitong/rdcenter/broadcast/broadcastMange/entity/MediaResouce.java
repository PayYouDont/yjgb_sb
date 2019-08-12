package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import com.gospell.chitong.rdcenter.broadcast.broadcastMange.config.ServerProperties;
import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * media_resouce
 * @author 
 */
@Data
public class MediaResouce implements Serializable {
    private Integer id;

    /**
     * 资源名称
     */
    private String fileName;

    /**
     * 资源存放路径
     */
    private String filePath;

    /**
     * 资源类型
     */
    private String fileType;

    /**
     * 资源大小
     */
    private Long fileSize;

    /**
     * 来源
     */
    private String source;

    /**
     * 审核状态(0：待审核，1：审核通过，2：审核未通过)
     */
    private Integer status;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    /**
     * 资源介绍
     */
    private String introduction;
    private String playPath;

    public String getPlayPath() {
        ServerProperties serverProperties = ApplicationContextRegister.getBean(ServerProperties.class);
        return serverProperties.getServer_ip()+":"+serverProperties.getServer_port()+"/EBM_media/"+fileName;
    }
    private static final long serialVersionUID = 1L;
}