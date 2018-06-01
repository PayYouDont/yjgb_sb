package com.gospell.chitong.rdcenter.broadcast.commonManage.annontation;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 
* @ClassName: Log 
* @Description: TODO(     ) 
* @author peiyongdong
* @date 2018年5月31日 下午2:49:35 
*
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}
