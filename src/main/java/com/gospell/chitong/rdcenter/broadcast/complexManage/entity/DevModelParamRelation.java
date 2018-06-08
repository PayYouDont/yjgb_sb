package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * dev_model_param_relation
 * @author 
 */
@Data
public class DevModelParamRelation implements Serializable {
    private Integer id;

    /**
     * devModel的id
     */
    private Integer dmId;

    /**
     * devModelParam的id
     */
    private Integer dmpId;

    private static final long serialVersionUID = 1L;
}