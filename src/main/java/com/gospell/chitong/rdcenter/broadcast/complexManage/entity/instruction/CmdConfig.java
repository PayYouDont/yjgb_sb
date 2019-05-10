package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;

import java.io.Serializable;

/**
 * cmd_config
 * @author 
 */
@Data
public class CmdConfig implements Serializable {
    private Integer id;

    /**
     * 指令名称中文
     */
    private String nameCh;

    /**
     * 指令名称英文
     */
    private String nameEn;

    /**
     * 指令标识符
     */
    private Integer tag;

    /**
     * 指令长度
     */
    private Integer length;

    private Integer cmdTypeId;

    /**
     * 指令内容
     */
    private String cmd;

    private static final long serialVersionUID = 1L;
}