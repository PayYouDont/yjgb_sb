package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;

/**
 * infosource
 * @author 
 */
public class Infosource implements Serializable {
    private Integer id;

    private String audioPid;

    private String infoSourceCode;

    private String infoSourceName;

    private String ip;

    private Integer port;

    private String serviceId;

    private String videoPid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAudioPid() {
        return audioPid;
    }

    public void setAudioPid(String audioPid) {
        this.audioPid = audioPid;
    }

    public String getInfoSourceCode() {
        return infoSourceCode;
    }

    public void setInfoSourceCode(String infoSourceCode) {
        this.infoSourceCode = infoSourceCode;
    }

    public String getInfoSourceName() {
        return infoSourceName;
    }

    public void setInfoSourceName(String infoSourceName) {
        this.infoSourceName = infoSourceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getVideoPid() {
        return videoPid;
    }

    public void setVideoPid(String videoPid) {
        this.videoPid = videoPid;
    }
}