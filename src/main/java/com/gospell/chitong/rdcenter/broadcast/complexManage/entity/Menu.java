package com.gospell.chitong.rdcenter.broadcast.complexManage.entity;

import java.io.Serializable;

/**
 * menu
 * @author 
 */
public class Menu implements Serializable {
    private Integer id;

    /**
     * 显示标题
     */
    private String text;

    /**
     * 父节点ID
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer number;

    /**
     * 关联地址
     */
    private String url;

    /**
     * 标题相关提示信息
     */
    private String menucaptionhint;

    /**
     * 英文标题
     */
    private String menucaptionen;

    /**
     * 属于哪个系统的menu
     */
    private String menusystem;

    /**
     * 默认图片
     */
    private String menuimage;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenucaptionhint() {
        return menucaptionhint;
    }

    public void setMenucaptionhint(String menucaptionhint) {
        this.menucaptionhint = menucaptionhint;
    }

    public String getMenucaptionen() {
        return menucaptionen;
    }

    public void setMenucaptionen(String menucaptionen) {
        this.menucaptionen = menucaptionen;
    }

    public String getMenusystem() {
        return menusystem;
    }

    public void setMenusystem(String menusystem) {
        this.menusystem = menusystem;
    }

    public String getMenuimage() {
        return menuimage;
    }

    public void setMenuimage(String menuimage) {
        this.menuimage = menuimage;
    }
}