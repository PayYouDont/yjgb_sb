package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gospell.chitong.rdcenter.broadcast.complexManage.config.ApplicationContextRegister;
import com.sun.mail.util.PropUtil;

import lombok.Data;

@Component
@Data
public class ProepertyUtil {
	@Value("${config.path}")
	private String PropertiesPath;
	public static final Logger logger = LoggerFactory.getLogger("ProepertyUtil");
	public static String getPath() {
		String path = ApplicationContextRegister.getBean(ProepertyUtil.class).getPropertiesPath();
		return path;
	}
	public static int writeToProperties(Map<String,Object> map,String propertyPath) {
		//取得一个Properties对象  
		Properties props = new Properties(); 
		//取得该配置文件的输入流  
		InputStream is = PropUtil.class.getClassLoader().getResourceAsStream(propertyPath);
		//获取路径
		String path = PropUtil.class.getClassLoader().getResource(propertyPath).getFile();
		try {
			File file = null;
			if(getPath()!=null) {
				file = new File(getPath());
			}else {
				file = new File(path);
			}
			 //将配置文件的输入流load到Properties对象中
			 props.load(new InputStreamReader(is,"utf-8"));
			 
			 Set<Entry<String, Object>> set = map.entrySet();
			 for (Entry<String, Object> entry : set) {
				 props.setProperty(entry.getKey(),entry.getValue().toString());  
			 }
			 OutputStream out = new FileOutputStream(file,false);  
			 props.store(new OutputStreamWriter(out,"utf-8"), null);  
	         is.close();  
	         out.close(); 
	         return 1;
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			return -1;
		}
	}
}