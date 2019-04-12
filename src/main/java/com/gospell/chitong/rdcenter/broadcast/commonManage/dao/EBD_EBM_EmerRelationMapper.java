package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import com.gospell.chitong.rdcenter.broadcast.commonManage.entity.EBD_EBM_EmerRelation;

public interface EBD_EBM_EmerRelationMapper extends BaseDao<EBD_EBM_EmerRelation,Integer>{
   EBD_EBM_EmerRelation selectByEmerId(Integer emerid);
}