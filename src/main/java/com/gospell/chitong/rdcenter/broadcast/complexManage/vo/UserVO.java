package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import lombok.Data;
@Data
public class UserVO {
	/**
     * 更新的用户对象
     */
	private Integer userId;
	/**
	 * 旧密码
	 */
	private String oldPwd;
	/**
	 * 新密码
	 */
	private String newPwd;
}
