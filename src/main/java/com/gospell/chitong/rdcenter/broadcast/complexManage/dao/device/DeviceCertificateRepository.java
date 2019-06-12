package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceCertificateRepository extends JpaRepository<DeviceCertificate,Integer> {
    //DeviceTask findByEBMID(String EBMID);
}
