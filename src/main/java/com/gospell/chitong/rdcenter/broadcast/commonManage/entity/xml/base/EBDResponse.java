package com.gospell.chitong.rdcenter.broadcast.commonManage.entity.xml.base;

public interface EBDResponse extends EBD {
    EBD createFullResponse();
    EBD createIncrementalResponse();
}
