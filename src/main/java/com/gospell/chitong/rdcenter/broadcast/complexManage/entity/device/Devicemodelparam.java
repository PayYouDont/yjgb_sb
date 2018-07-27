package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * devicemodelparam
 * @author 
 */
@Data
public class Devicemodelparam implements Serializable {
    private Integer id;

    private String paramName;

    private String paramVariable;

    private String paramLength;

    /**
     * 表单校验字符串
     */
    private String paramFormCheck;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private static final long serialVersionUID = 1L;

}