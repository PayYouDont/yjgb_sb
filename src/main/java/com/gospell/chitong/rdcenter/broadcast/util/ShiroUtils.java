package com.gospell.chitong.rdcenter.broadcast.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;

public class ShiroUtils {
   /* @Autowired
    private static SessionDAO sessionDAO;*/

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static User getUser() {
        Object object = getSubjct().getPrincipal();
        return (User)object;
    }
    public static int getUserId() {
        return getUser().getId();
    }
    
    public static int getUserRoleId() {
        return getUser().getRoleId();
    }
    
    public static void logout() {
        getSubjct().logout();
    }

   /* public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }*/
}
