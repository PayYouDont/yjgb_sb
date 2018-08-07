
package com.gospell.chitong.rdcenter.broadcast.complexManage.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** 
* @ClassName: ExceptionResolver 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年8月6日 下午4:49:14 
*  
*/
@Component
public class ExceptionResolver implements HandlerExceptionResolver{

	/** 
	 * <p>Title: resolveException</p> 
	 * <p>Description: </p> 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return 
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception) 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年8月6日 下午4:49:40
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url  
        if(ex instanceof UnauthorizedException){  
            ModelAndView mv = new ModelAndView("error/error");  
            return mv;  
        } 
        ex.printStackTrace();  
        ModelAndView mv = new ModelAndView("error/error");  
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));  
        return mv;
	}
	
}
