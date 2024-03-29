package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name="sys_platform")
@EqualsAndHashCode(callSuper = false)
public class Platform extends BaseEntity {
    //平台名称
    private String name;
    //logo存放路径
    private String logoPath;
    //区域码
    private String addressCode;
    //区域名字
    private String address;
    //联系人
    private String contact;
    //联系电话
    private String phoneNumber;
    //平台路径
    private String url;
    //纬度
    private String lat;
    //经度
    private String lng;
    //平台资源编码后10位编码
    private String EBRPSLastCode;
    //适配器资源编码后10位编码
    private String EBRASLastCode;
    //终端资源编码后10位编码
    private String EBRDTLastCode;
    //传输设备资源编码后10位编码
    private String EBRBSSLastCode;
    //上级平台地址
    private String superiorUrl;
    //上级平台区域编码
    private String superAreaCode;
    //坐标
    @Transient
    private String coordinate;
    @Transient
    public String getCoordinate() {
        if(lat!=null&&lng!=null) {
            coordinate = lng+","+lat;
        }
        return coordinate;
    }
}
