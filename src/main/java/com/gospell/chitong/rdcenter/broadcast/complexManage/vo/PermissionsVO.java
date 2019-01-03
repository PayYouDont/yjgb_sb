package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import lombok.Data;

@Data
public class PermissionsVO {
    private boolean view = false;
    private boolean modify = false;
    private boolean add = false;
    private boolean delete = false;

    public boolean hasPermission(){
        return view||modify||add||delete;
    }

    public boolean hasPermissionAll(){
        return view&&modify&&add&&delete;
    }
}
