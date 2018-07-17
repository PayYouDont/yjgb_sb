package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * send_tar
 * @author 
 */
@Data
public class SendTar implements Serializable {
    private String ebdid;

    private String ebdType;

    private Integer resultCode;

    private String resultDesc;

    private String destId;

    private Date sendDate;

    private static final long serialVersionUID = 1L;
}