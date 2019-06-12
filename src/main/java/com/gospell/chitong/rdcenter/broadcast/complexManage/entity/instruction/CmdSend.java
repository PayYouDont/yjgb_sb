package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction;

import java.io.Serializable;

import lombok.Data;

/**
 * cmd_send
 * @author
 */
@Data
public class CmdSend implements Serializable {
    private Integer id;

    private Integer cmdConfigId;
    /**
     * 指令发送状态( -1:未发送，0：表示终端成功接收并且正确处理；13：表示请求数据包出现错误；60：表示终端出现错误，无法处理。)
     */
    private Integer status;

    private String result;
    /**
     * 指令发送json字符串
     */
    private String cmdChar;

    //以下内容不存入数据库
    private CmdConfig cmdConfig;
    private Integer tag;
    private String type;
    private static final long serialVersionUID = 1L;

    public Integer getTag() {
    	if(tag==null&&cmdConfig!=null) {
    		tag = cmdConfig.getTag();
    	}
    	return tag;
    }
    public String getType() {
    	if(type==null&&cmdConfig!=null) {
    		type = cmdConfig.getNameEn();
    	}
    	return type;
    }
}