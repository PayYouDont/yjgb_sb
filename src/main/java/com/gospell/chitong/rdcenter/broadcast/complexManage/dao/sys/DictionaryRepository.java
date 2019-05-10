package com.gospell.chitong.rdcenter.broadcast.complexManage.dao.sys;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
    List<Dictionary> findAllByField(String field);
}
