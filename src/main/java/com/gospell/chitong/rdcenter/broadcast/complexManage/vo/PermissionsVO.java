package com.gospell.chitong.rdcenter.broadcast.complexManage.vo;

import lombok.Data;

@Data
public class PermissionsVO {
    private boolean isView = false;
    private boolean isModify = false;
    private boolean isAdd = false;
    private boolean isDelete = false;

    public boolean hasPermission(){
        return isView||isModify||isAdd||isDelete;
    }

    public boolean hasPermissionAll(){
        return isView&&isModify&&isAdd&&isDelete;
    }
}
