package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.GroupRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Group;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.GroupService;
import com.gospell.chitong.rdcenter.broadcast.util.UpdateTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GroupServiceImpl
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 11:25
 **/

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository dao;
    @Override
    public List<Group> list() {
        return dao.findAll ();
    }

    @Override
    public int count() {
        return (int)dao.count ();
    }

    @Override
    public void saveOrUpdate(Group entity) throws Exception {
        if(entity.getId ()!=null){
            Group source = selectById (entity.getId ());
            UpdateTool.copyNullProperties (source,entity);
        }
        save (entity);
    }

    @Override
    public int delete(Integer id) throws Exception {
        dao.deleteById (id);
        return 1;
    }

    @Override
    public int save(Group record) throws Exception {
        dao.save (record);
        return 0;
    }

    @Override
    public Group selectById(Integer id) {
        return dao.findById (id).get ();
    }

    @Override
    public Page<Group> page(Integer pageIndex, Integer pageSize) {
        return dao.findAll (PageRequest.of (pageIndex,pageSize));
    }
}
