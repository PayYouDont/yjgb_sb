package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_resource_code
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CMDResourceCode extends CMD {

    /**
     * 物理地址长度
     */
    private Integer terminalAddressLength;

    /**
     * 物理地址 n=terminal_address_length
     */
    private String terminalAddress;

    /**
     * 保留，取值均为“1”；

     */
    private Integer reserved;

    private String resourceCode;

    private static final long serialVersionUID = 1L;
}