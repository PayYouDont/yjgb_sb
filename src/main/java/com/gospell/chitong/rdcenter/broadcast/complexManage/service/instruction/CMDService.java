package com.gospell.chitong.rdcenter.broadcast.complexManage.service.instruction;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.instruction.CMD;

public interface CMDService{
    int delete(Integer id,Class<? extends CMD> clazz) throws Exception;

    int save(CMD record,Class<? extends CMD> clazz) throws Exception;

    CMD selectById(Integer id,Class<? extends CMD> clazz);
}
