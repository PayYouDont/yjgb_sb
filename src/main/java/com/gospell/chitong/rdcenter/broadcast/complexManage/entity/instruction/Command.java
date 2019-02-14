/**   
* @Title: Command.java 
* @Package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction 
* @Description: TODO(     ) 
* @author peiyongdong  
* @date 2019年2月14日 上午8:38:37 
*/
package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.util.ArrayList;

import lombok.Data;

/** 
* @ClassName: Command 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2019年2月14日 上午8:38:37 
*  
*/
@Data
public class Command {

	private ArrayList<Commands> Commands;

	@Data
	public static class Commands {
		private int CMD_Tag;
		private String CMD_Char;
	}


}
