package com.gospell.chitong.rdcenter.broadcast.monitorManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 事件等级统计视图
 * view_emer_level
 * @author 
 */
@Data
public class ViewEmerLevel implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO(     ) 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
     * 应急消息严重程度文字说明
     */
    private String level;

    /**
     * 创建时间
     */
    private Date createTime;
    
    private Integer count;

}