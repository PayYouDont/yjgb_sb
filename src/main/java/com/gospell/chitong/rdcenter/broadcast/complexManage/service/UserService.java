package com.gospell.chitong.rdcenter.broadcast.complexManage.service;

import java.util.List;
import java.util.Map;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;

public interface UserService {
	int deleteById(Integer id) throws Exception;;

    User selectById(Integer id);

    User findByName(String name);
    
    int save(User user) throws Exception;
    
    int resetPwd(UserVO userVO,User user) throws Exception;
    
    List<User> list(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}
