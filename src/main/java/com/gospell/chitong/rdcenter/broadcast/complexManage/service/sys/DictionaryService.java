package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DictionaryService extends BaseService<Dictionary,Integer> {
    List<Dictionary> list();
    int count();
    void saveOrUpdate(Dictionary entity) throws Exception;
    Page<Dictionary> page(Integer pageIndex, Integer pageSize);
    List<Dictionary> listByField(String field);
}
