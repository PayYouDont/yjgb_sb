
package com.gospell.chitong.rdcenter.broadcast.commonManage.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: BaseService 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年9月11日 上午11:19:21 
*  
*/
public interface BaseService<T extends Serializable, K>{
	int delete(K id) throws Exception;

    int save(T record) throws Exception;
   
    T selectById(K id);

    default List<T> list(Map<String,Object> map){
    	return null;
    };
    
    default int count(Map<String,Object> map) {
    	return 0;
    };
}
