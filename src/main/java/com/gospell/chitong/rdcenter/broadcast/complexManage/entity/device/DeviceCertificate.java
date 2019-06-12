package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName DeviceCertificate
 * @Description TODO
 * @Author pay
 * @DATE 2019/6/6 14:38
 **/
@Data
@Entity
@Table(name="device_certificate")
@EqualsAndHashCode(callSuper = false)
public class DeviceCertificate extends BaseEntity {
    //证书验证平台路径
    private String certificateUrl;
    //平台证书SN
    private String pathSN;
    //安全设备SN
    private String SMSN;
    //下发状态(0:成功1:失败)
    private Integer status;
    //下发时间
    private Date sendTime;
    //保留字段
    private String reserve;
    public String getUrl(){
        return certificateUrl+"?path="+pathSN+"&opt=REG&SMSN="+ SMSN;
    }
}
