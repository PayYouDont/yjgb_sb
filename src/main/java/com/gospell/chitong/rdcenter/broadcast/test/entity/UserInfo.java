package com.gospell.chitong.rdcenter.broadcast.test.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;

    private String username;

    private String usercode;

    private String userpassword;

    private Integer usersex;

    private String phonenum;

    private String userarecode;

    private String userarecodelevel;

    private String userarecodename;

    private Long rolegroupinfoId;

    private Date timemodify;

    private String usercreated;

    private String usermodify;

    private Date createdtime;
}