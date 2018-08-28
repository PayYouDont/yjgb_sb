
package com.gospell.chitong.rdcenter.broadcast.commonManage.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.log.UserLog;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.Role;
import com.gospell.chitong.rdcenter.broadcast.complexManage.entity.sys.User;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.log.SysLogService;
import com.gospell.chitong.rdcenter.broadcast.complexManage.service.sys.RoleService;
import com.gospell.chitong.rdcenter.broadcast.util.IPUtils;
import com.gospell.chitong.rdcenter.broadcast.util.ShiroUtils;

/** 
* @ClassName: LogAop 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月21日 上午10:53:42 
*  
*/
@Aspect
@Component
public class SysLogAop {
	private static final Logger logger = LoggerFactory.getLogger(SysLogAop.class);
	@Resource
	private RoleService roleService;
	@Resource
	private SysLogService logService;
	
	@Pointcut("@annotation(com.gospell.chitong.rdcenter.broadcast.commonManage.annontation.Log)")
    public void logPointCut() {
    }
	
	@Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        //long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point);
        return result;
    }
	public void saveLog(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        UserLog userLog = new UserLog();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            userLog.setUrlfunction(log.value());
        }
        // 请求的方法名
        /*String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();*/
        String params =  "";
        try {
        	// 请求的参数
            Object[] args = joinPoint.getArgs();
        	if(args.length>0&&args[0]!=null) {
                params = JSON.toJSONString(args[0]);
        	}
            userLog.setDes(params);
        } catch (Exception e) {
        	logger.error(e.getMessage(),e);
        }
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 设置IP地址
        userLog.setClientip(IPUtils.getIpAddr(request));
        //设置路径
        userLog.setUrl(request.getRequestURI().toString());
        // 用户名
        User currUser = ShiroUtils.getUser();
        if (null == currUser) {
        	 userLog.setUserId(-1);
             userLog.setUserName("获取用户信息为空");
        } else {
        	User user = ShiroUtils.getUser();
            userLog.setUserId(user.getId());
            userLog.setUserName(user.getName());
            userLog.setRoleId(user.getRoleId().toString());
            Role role = roleService.findById(user.getRoleId());
            userLog.setRoleName(role.getName());
        }
        // 保存系统日志
        try {
			logService.save(userLog);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
