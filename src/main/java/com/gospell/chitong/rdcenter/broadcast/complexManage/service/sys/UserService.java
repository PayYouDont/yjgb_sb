package com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys;

import com.gospell.chitong.rdcenter.broadcast.commonManage.service.BaseService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.vo.UserVO;

public interface UserService extends BaseService<User,Integer>{
    User findByName(String name);
    int resetPwd(UserVO userVO,User user) throws Exception;
}
