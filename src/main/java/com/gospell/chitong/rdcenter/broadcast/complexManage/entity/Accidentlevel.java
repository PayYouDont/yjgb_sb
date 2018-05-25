package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * accidentlevel
 * @author 
 */
@Data
public class Accidentlevel implements Serializable {
    private Integer id;

    /**
     * 应急消息严重程度文字说明
     */
    private String level;

    /**
     * 应急消息严重程度等级
     */
    private Integer levelcode;

    private Integer number;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    private static final long serialVersionUID = 1L;
}