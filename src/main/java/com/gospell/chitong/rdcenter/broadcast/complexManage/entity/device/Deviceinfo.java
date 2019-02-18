package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;
import java.util.Date;

import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import lombok.Data;
import lombok.Setter;

/**
 * deviceinfo
 * @author 
 */
@Data
public class Deviceinfo implements Serializable {
    private Integer id;

    /**
     * 设备名称
     */
    private String devname;

    /**
     * 设备序列号
     */
    private String devdsn;

    /**
     * 设备寻址号
     */
    private String devcode;

    /**
     * 设备地址号
     */
    private String devhexcode;

    /**
     * 设备地址行政编号
     */
    private String devaddresscode;

    /**
     * 设备地址名称
     */
    private String devaddress;

    /**
     * 设备状态(8位二进制格式，如:00000001)
     */
    private String status;

    /**
     * 纬度
     */
    @Setter
    private String lat;

    /**
     * 经度
     */
    @Setter
    private String lng;

    /**
     * 当前应急消息挂载的应急消息的id
     */
    private String messageid;

    /**
     * 设备型号id
     */
    private Integer devicemodelId;

    private Integer deviceid;

    /**
     * 状态描述
     */
    private String statusscript;

    /**
     * 发现序列号时间
     */
    private Date timefind;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    private String parentpath;
    
	private Integer onregister;//是否注册
    /***********不存数据库的属性*****************/
    //坐标
    private String coordinate;
    /*@Setter
	private String onregister;//是否注册 （第7位）*/
	@Setter
	private String online;	  //是否在线 （第6位）
    @Setter
	private String onwork;	  //是否工作	 （第5位）
    @Setter
	private String onwarning; //是否报警 （第4位）
    
    private Devicemodel deviceModel;//设备型号
    private String resouceCode;
    
    private static final long serialVersionUID = 1L;
    
   /* public String getOnregister() {
    	if(status!=null&&!"".equals(status)) {
        	return getStatus().substring(7,8);
    	}
    	return null;
    }*/
    public String getOnline() {
    	if(status!=null&&!"".equals(status)) {
        	return status.substring(6,7);
    	}
    	return status;
    }
    public String getOnwork() {
    	if(status!=null&&!"".equals(status)) {
        	return status.substring(5,6);
    	}
    	return status;
    }
    public String getOnwarning() {
    	if(status!=null&&!"".equals(status)) {
        	return status.substring(4,5);
    	}
    	return status;
    }
    //获取纬度
    public String getLat() {
    	if(coordinate!=null&&!"".equals(coordinate)&&coordinate.indexOf(",")!=-1) {
        	this.lat = coordinate.split(",")[1];
    	}
    	return this.lat;
    }
    //获取精度
    public String getLng() {
    	if(coordinate!=null&&!"".equals(coordinate)&&coordinate.indexOf(",")!=-1) {
        	this.lng = coordinate.split(",")[0];
    	}
    	return this.lng;
    }
    public String getResouceCode() {
    	if(resouceCode==null) {
    		resouceCode = EBDcodeUtil.getEBRID(devaddresscode);
    	}
    	return resouceCode;
    }
}
