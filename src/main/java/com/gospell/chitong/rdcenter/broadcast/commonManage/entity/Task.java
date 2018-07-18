package com.gospell.chitong.rdcenter.broadcast.commonManage.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * task
 * @author 
 */
@Data
public class Task implements Serializable {
    private Long id;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 任务调用的方法名
     */
    private String methodName;

    /**
     * 任务是否有状态
     */
    private String isConcurrent;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务状态
     */
    private String jobStatus;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * Spring bean
     */
    private String springBean;

    /**
     * 任务名
     */
    private String jobName;

    private static final long serialVersionUID = 1L;
}