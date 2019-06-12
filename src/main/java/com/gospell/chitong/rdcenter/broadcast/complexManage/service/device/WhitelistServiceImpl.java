package com.gospell.chitong.rdcenter.broadcast.complexManage.service.device;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.device.WhitelistRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.device.Whitelist;
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
public class WhitelistServiceImpl implements WhitelistkService{
    @Resource
    private WhitelistRepository repository;

    @Override
    public List<Whitelist> list() {
        return repository.findAll ();
    }

    @Override
    public int count() {
        return (int)repository.count ();
    }

    @Override
    public void saveOrUpdate(Whitelist entity) throws Exception {
        if(entity.getId ()!=null){
            Whitelist source = selectById (entity.getId ());
            UpdateTool.copyNullProperties(source,entity);
        }
        save (entity);
    }

    @Override
    public Page<Whitelist> page(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort (Sort.Direction.DESC,"id");
        return repository.findAll (PageRequest.of (pageIndex,pageSize,sort));
    }

    @Override
    public int delete(Integer id) throws Exception {
        repository.deleteById (id);
        return 1;
    }

    @Override
    public int save(Whitelist record) throws Exception {
        repository.save (record);
        return 1;
    }

    @Override
    public Whitelist selectById(Integer id) {
        return repository.findById (id).get ();
    }
}
