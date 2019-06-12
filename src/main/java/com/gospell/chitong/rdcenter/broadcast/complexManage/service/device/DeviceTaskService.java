package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceTask;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeviceTaskService extends BaseService<DeviceTask,Integer> {
    List<DeviceTask> list();
    int count();
    void saveOrUpdate(DeviceTask entity) throws Exception;
    Page<DeviceTask> page(Integer pageIndex, Integer pageSize);

}
