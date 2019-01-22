/**   
* @Title: Cmd.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午3:54:57 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import lombok.Data;

/** 
* @ClassName: Cmd 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月21日 下午3:54:57 
*  
*/
@Data
public abstract class CMD {
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

}
