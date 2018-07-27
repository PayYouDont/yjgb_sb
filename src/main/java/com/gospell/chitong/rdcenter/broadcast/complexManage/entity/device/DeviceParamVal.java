package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device;

import java.io.Serializable;

import lombok.Data;

/**
 * device_param_val
 * @author 
 */
@Data
public class DeviceParamVal implements Serializable {
    private Integer id;

    private Integer deviceInfoId;

    private String paramName;

    private String paramVariable;

    private String val;
    /**
     * 表单校验字符串
     */
    private String paramFormCheck;

    private static final long serialVersionUID = 1L;
    
    //设备
    private Deviceinfo deviceInfo;
}