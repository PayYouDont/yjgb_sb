package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceCertificate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeviceCertificateService extends BaseService<DeviceCertificate,Integer> {
    List<DeviceCertificate> list();
    int count();
    void saveOrUpdate(DeviceCertificate entity) throws Exception;
    Page<DeviceCertificate> page(Integer pageIndex, Integer pageSize);
    void send(Integer id) throws Exception;
}
