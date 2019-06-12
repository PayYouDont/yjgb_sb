package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTaskRepository extends JpaRepository<DeviceTask,Integer> {
    //DeviceTask findByEBMID(String EBMID);
}
