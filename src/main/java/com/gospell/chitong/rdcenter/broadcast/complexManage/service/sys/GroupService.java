package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Group;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupService extends BaseService<Group,Integer> {
    List<Group> list();
    int count();
    void saveOrUpdate(Group entity) throws Exception;
    Page<Group> page(Integer pageIndex, Integer pageSize);
}
