package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;
import java.util.Date;

import com.gospell.chitong.rdcenter.broadcast.util.EBDcodeUtil;

import com.gospell.chitong.rdcenter.broadcast.util.StringUtil;
import lombok.Data;
import lombok.Setter;
import org.springframework.util.StringUtils;

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
    /**
     * 是否注册
     */
    private String onregister;

    private String parentpath;

    /***********不存数据库的属性*****************/
    //坐标
    private String coordinate;
	@Setter
	private String online;	  //是否在线 （第6位）
    @Setter
	private String onwork;	  //是否工作	 （第5位）
    @Setter
	private String onwarning; //是否报警 （第4位）

    private Devicemodel deviceModel;//设备型号
    private String resouceCode;
    private String statusDesc;

    private static final long serialVersionUID = 1L;

    public String getOnline() {
    	if(!StringUtils.isEmpty (status)) {
        	return status.substring(6,7);
    	}
    	return status;
    }
    public String getOnwork() {
    	if(!StringUtils.isEmpty (status)) {
        	return status.substring(5,6);
    	}
    	return status;
    }
    public String getOnwarning() {
    	if(!StringUtils.isEmpty (status)) {
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
    	if(resouceCode==null&&devaddresscode!=null) {
    		resouceCode = EBDcodeUtil.getEBRID(devaddresscode);
    	}
    	return resouceCode;
    }

    /**
     * /*
     * 		 * 1：开机/运行正常
     * 		 * 2：关机/停止运行
     * 		 * 3：故障
     * 		 * 4：故障恢复
     * 		 * 5：播发中
     *
     **/

    public String getStatusDesc(){
        if(getStatusToEBD()==1){
            return "开机/运行正常";
        }else if(getStatusToEBD()==2){
            return "关机/停止运行";
        }else if(getStatusToEBD()==3){
            return "故障";
        }else if(getStatusToEBD()==4){
            return "故障恢复";
        }else{
            return "播发中";
        }
    }
    public Integer getStatusToEBD(){
        if("1".equals (online)&&"1".equals (onwarning)&&"1".equals (onwork)){
            return 1;
        }else if("0".equals (online)){
            return 2;
        }else if("0".equals (onwarning)){
            return 3;
        }else if("1".equals (online)&&"1".equals (onwarning)&&"0".equals (onwork)){
            return 4;
        }else{
            return 5;
        }
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setStatus(Integer status){
        int statusCode = (int) Math.pow (2,status);
        String workStatus = StringUtil.patch ("0",8,Integer.valueOf (Integer.toBinaryString (statusCode)));
        this.status = workStatus;
    }
}
