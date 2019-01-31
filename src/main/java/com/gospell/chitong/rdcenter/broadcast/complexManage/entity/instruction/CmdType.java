package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.io.Serializable;

import lombok.Data;

/**
 * cmd_type
 * @author 
 */
@Data
public class CmdType implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;

	private Integer id;

    /**
     * 中文名称(key)
     */
    private String nameCh;

    /**
     * 英文名称(value)
     */
    private String nameEn;

    /**
     * 输入框类型(0:文本框,1:数字框,2:日期框,3:资源框)
     */
    private Integer boxType = 0;

    /**
     * 资源路径(输入框类型为资源型时才会有值)
     */
    private String sourceUrl;

    /**
     * 资源list中某些字段名
     */
    private String sourceFields;
}