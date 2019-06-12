package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Whitelist;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WhitelistkService extends BaseService<Whitelist,Integer> {
    List<Whitelist> list();
    int count();
    void saveOrUpdate(Whitelist entity) throws Exception;
    Page<Whitelist> page(Integer pageIndex, Integer pageSize);

}
