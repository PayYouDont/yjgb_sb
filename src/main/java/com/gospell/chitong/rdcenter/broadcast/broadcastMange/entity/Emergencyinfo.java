package com.gospell.chitong.rdcenter.broadcast.broadcastMange.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * emergencyinfo
 * 
 * @author
 */
@Data
public class Emergencyinfo implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 信息名称
	 */
	private String emergencyname;

	/**
	 * 事件编号(随机数)
	 */
	private String emergencycode;

	/**
	 * 发送时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 音量大小(1-100）
	 */
	private String sound;

	/**
	 * 持续时间
	 */
	private String duration;

	/**
	 * 选择的覆盖范围的区域编码
	 */
	private String addresscode;

	/**
	 * 输入资源节目id的字符串,每个id用';'分割
	 */
	private Integer programId;

	/**
	 * 输入资源描述
	 */
	private String programdescription;

	/**
	 * 输出资源id
	 */
	private Integer infosourceId;

	/**
	 * accidentlevel的id
	 */
	private Integer accidentlevelId;

	private Integer accidenttypeId;

	private Integer displaymethodId;

	private Integer displaylanguageId;

	private Integer emergencylocationId;

	/**
	 * 修改人
	 */
	private String updateBy;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 信息状态 (1:待提交 2:待审核 3:未通过审核 4:已审核 5:待发送 6:已发送 7:发送成功 8:等待播发 9:正在播发 10:播发失败
	 * 11:播发结束)
	 */
	private Integer status;

	/**
	 * 制作平台名称
	 */
	private String unitname;

	private Integer ebmId;

	private String areacode;

	/**
	 * 信息类型(0:预案1:非预案)
	 */
	private Integer flag;

	private String addresscodename;

	/**
	 * 信息内容
	 */
	private String content;

	private static final long serialVersionUID = 1L;
}