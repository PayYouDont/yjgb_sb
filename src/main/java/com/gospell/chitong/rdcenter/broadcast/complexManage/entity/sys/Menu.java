package com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.PermissionsVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * menu
 * @author 
 */
@Data
public class Menu {
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
    /**
     * 菜单权限
     */
    private PermissionsVO permissions = new PermissionsVO();

    private String state = "open";

    private Boolean checked = false;

    private List<Menu> children = new ArrayList<> ();
}