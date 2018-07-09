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

import com.sun.mail.util.PropUtil;

public class ProepertyUtil {
	public static int writeToProperties(Map<String,Object> map,String propertyPath) {
		//取得一个Properties对象  
		Properties props = new Properties(); 
		//取得该配置文件的输入流  
		InputStream is = PropUtil.class.getClassLoader().getResourceAsStream(propertyPath);
		//获取路径
		String path = PropUtil.class.getClassLoader().getResource(propertyPath).getFile();
		try {
			 //将配置文件的输入流load到Properties对象中
			 props.load(new InputStreamReader(is,"utf-8"));
			 
			 Set<Entry<String, Object>> set = map.entrySet();
			 for (Entry<String, Object> entry : set) {
				 props.setProperty(entry.getKey(),entry.getValue().toString());  
			 }
			 File file = new File(path);
			 OutputStream out = new FileOutputStream(file,false);  
			 props.store(new OutputStreamWriter(out,"utf-8"), null);  
	         is.close();  
	         out.close(); 
	         return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}