package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @ClassName DeviceTask
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/29 15:27
 **/
@Data
@Entity
@Table(name="device_task")
@EqualsAndHashCode(callSuper = false)
public class DeviceTask extends BaseEntity {
    private Integer deviceId;
    /**
     * 1：任务开始
     * 2：任务结束
     **/
    private Integer status;
    private Integer cmdSendId;
     /**
     * 任务类型:
     * 1：应急节目源
     * 2：日常节目源
     * 3：电话
     * 4：短信
     * 5：调音台
     * 6：U 盘
     **/
    private Integer taskType;
    private String EBMID;
    private Date taskStartTime;
    @Transient
    private Deviceinfo deviceinfo;
}
