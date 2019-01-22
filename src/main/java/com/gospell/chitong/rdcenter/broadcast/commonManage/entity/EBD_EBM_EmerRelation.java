package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * ebd_ebm_emer_relation
 * @author 
 */
@Data
public class EBD_EBM_EmerRelation implements Serializable {
    private Integer id;

    private Integer emerId;

    private String ebmId;

    private String ebdId;

    private static final long serialVersionUID = 1L;
}