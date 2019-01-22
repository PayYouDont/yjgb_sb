package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * cmd_parameter
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CMDParameter extends CMD {
    /** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;

    /**
     * 查询参数个数
     */
    private Integer parameterNumber;

    /**
     * 参数标识
     */
    private String parameterTag;

    /**
     * 需要配置的终端资源编码的个数
     */
    private Integer terminalNumber;

    private Integer reserved;

    private String resourceCode;

}