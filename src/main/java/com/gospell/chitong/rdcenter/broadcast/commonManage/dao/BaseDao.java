
package com.gospell.chitong.rdcenter.broadcast.commonManage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: BaseDao 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月11日 上午11:19:58 
*  
*/
public interface BaseDao<T, E extends Serializable> {
    int deleteByPrimaryKey(E id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(E id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    List<T> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}
