package com.gospell.chitong.rdcenter.broadcast.commonManage.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateFormatConfig {
	/**
	 * @Title: addNewConvert 
	 * @Description: TODO(处理前端传入时间无法转换成date问题) 
	 * @param @return    设定文件 
	 * @return Converter<String,Date>    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月5日 上午9:07:53
	 */
	@Bean
	public Converter<String, Date> addNewConvert() {
	    return new Converter<String, Date>() {
	        @Override
	        public Date convert(String source) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date date = null;
	            try {
	                date = sdf.parse( source);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            return date;
	        }
	    };
	}
}
