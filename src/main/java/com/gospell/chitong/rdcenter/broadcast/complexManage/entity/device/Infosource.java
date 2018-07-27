package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;

import lombok.Data;

/**
 * infosource
 * @author 
 */
@Data
public class Infosource implements Serializable {
    private Integer id;

    private String audioPid;

    private String infoSourceCode;

    private String infoSourceName;

    private String ip;

    private Integer port;

    private String serviceId;

    private String videoPid;

    private static final long serialVersionUID = 1L;
}