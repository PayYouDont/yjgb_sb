package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
     * 	1：开机/运行正常
     * 2：关机/停止运行
     * 3：故障
     * 4：故障恢复
     * 5：播发中
     **/
    private Integer status;

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
    /**
     * 是否注册
     */
    private String onregister;

    private String parentpath;

    /***********不存数据库的属性*****************/
    //坐标
    private String coordinate;
    private Devicemodel deviceModel;//设备型号
    private String resouceCode;
    private String statusDesc;

    private static final long serialVersionUID = 1L;
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
    public String getResouceCode(String type) {
    	if(resouceCode==null&&devaddresscode!=null) {
    		resouceCode = EBDcodeUtil.getEBRID(devaddresscode,type);
    	}
    	return resouceCode;
    }

    /**
     * 	1：开机/运行正常
     * 2：关机/停止运行
     * 3：故障
     * 4：故障恢复
     * 5：播发中
     **/

    public String getStatusDesc(){
        if(status==1){
            return "开机/运行正常";
        }else if(status==2){
            return "关机/停止运行";
        }else if(status==3){
            return "故障";
        }else if(status==4){
            return "故障恢复";
        }else if(status==5){
            return "播发中";
        }
        return null;
    }

}
