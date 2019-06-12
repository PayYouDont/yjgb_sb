package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.DeviceTaskRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.DeviceTask;
import com.gospell.chitong.rdcenter.broadcast.util.UpdateTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeviceTaskServiceImpl
 * @Description TODO
 * @Author pay
 * @DATE 2019/5/29 15:53
 **/
@Service
public class DeviceTaskServiceImpl implements DeviceTaskService{
    @Resource
    private DeviceTaskRepository repository;

    @Override
    public List<DeviceTask> list() {
        return repository.findAll ();
    }

    @Override
    public int count() {
        return (int)repository.count ();
    }

    @Override
    public void saveOrUpdate(DeviceTask entity) throws Exception {
        if(entity.getId ()!=null){
            DeviceTask source = selectById (entity.getId ());
            UpdateTool.copyNullProperties(source,entity);
        }
        save (entity);
    }

    @Override
    public Page<DeviceTask> page(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort (Sort.Direction.DESC,"id");
        return repository.findAll (PageRequest.of (pageIndex,pageSize,sort));
    }

    @Override
    public int delete(Integer id) throws Exception {
        repository.deleteById (id);
        return 1;
    }

    @Override
    public int save(DeviceTask record) throws Exception {
        repository.save (record);
        return 1;
    }

    @Override
    public DeviceTask selectById(Integer id) {
        return repository.findById (id).get ();
    }
}
