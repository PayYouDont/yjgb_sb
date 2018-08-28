
package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import java.util.Date;

import lombok.Data;

/** 
* @ClassName: QueryVO 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月28日 上午10:57:04 
*  
*/
@Data
public class QueryVO {
	private String condition;
	private Date startTime;
	private Date endTime;
	private String field;
	private String fieldValue;
}
