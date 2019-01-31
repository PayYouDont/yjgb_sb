/**   
* @Title: Cmd.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年1月21日 下午3:54:57 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.io.Serializable;

import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.GsonIgnore;
import com.gospell.chitong.rdcenter.broadcast.util.JsonUtil;

import lombok.Data;

/** 
* @ClassName: Cmd 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年1月21日 下午3:54:57 
*  
*/
@Data
public abstract class CMD implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;
	@GsonIgnore
	private Integer id;

    /**
     * 指令名称
     */
	@GsonIgnore
    private String name;

    /**
     * 指令标识符
     */
    private Integer tag;

    /**
     * 指令长度
     */
    @GsonIgnore
    private Integer length;
    @GsonIgnore
    private String cmd;
    public String getCmd() {
    	return JsonUtil.toJson(this);
    }
}
