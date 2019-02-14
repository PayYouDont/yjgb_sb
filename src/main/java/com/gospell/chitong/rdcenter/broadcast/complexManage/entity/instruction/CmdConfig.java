package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.io.Serializable;

import lombok.Data;

/**
 * cmd_config
 * @author 
 */
@Data
public class CmdConfig implements Serializable {
    private Integer id;

    /**
     * 指令名称
     */
    private String name;

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