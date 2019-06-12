package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys.DictionaryRepository;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.DictionaryService;
import com.gospell.chitong.rdcenter.broadcast.util.UpdateTool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DictionaryServiceImpl
 * @Description TODO
 * @Author pay
 * @DATE 2019/4/26 11:25
 **/
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryRepository dao;
    @Override
    public List<Dictionary> list() {
        return dao.findAll ();
    }

    @Override
    public int count() {
        return (int)dao.count ();
    }

    @Override
    public int delete(Integer id) throws Exception {
        dao.deleteById (id);
        return 1;
    }

    @Override
    public int save(Dictionary record) throws Exception {
        dao.save (record);
        return 1;
    }

    @Override
    public Dictionary selectById(Integer id) {
        return dao.findById (id).get ();
    }

    @Override
    public void saveOrUpdate(Dictionary entity) throws Exception {
        if(entity.getId ()!=null){
            Dictionary source = selectById (entity.getId ());
            UpdateTool.copyNullProperties(source,entity);
        }
        save (entity);
    }

    @Override
    public Page<Dictionary> page(Integer pageIndex, Integer pageSize) {
        Sort sort = new Sort (Sort.Direction.DESC,"id");
        return dao.findAll (PageRequest.of (pageIndex,pageSize,sort));
    }

    @Override
    public List<Dictionary> listByField(String field) {
        return dao.findAllByField (field);
    }
}
