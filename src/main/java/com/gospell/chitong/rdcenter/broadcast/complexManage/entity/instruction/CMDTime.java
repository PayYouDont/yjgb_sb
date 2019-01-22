package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_time
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDTime extends CMD {
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
     * 指令长度,单位为字节
     */
    private Integer length;

    /**
     * 年，如 2014 年：0x7de
     */
    private Integer wyear;

    /**
     * 月
     */
    private Integer imonth;

    /**
     * 日
     */
    private Integer iday;

    /**
     * 时
     */
    private Integer ihour;

    /**
     * 分
     */
    private Integer iminute;

    /**
     * 秒
     */
    private Integer isecond;

    private static final long serialVersionUID = 1L;
}