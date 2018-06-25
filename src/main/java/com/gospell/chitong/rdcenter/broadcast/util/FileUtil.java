package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.FileUtil");
	
	public static String copyFile(InputStream in,String outPaht,String fileName) {
		File file = new File(outPaht);
		if(!file.exists()) {
			file.mkdirs();
		}
		File outFile = new File(outPaht+File.separatorChar+fileName);
		try {
			wirteFile(in, new FileOutputStream(outFile));
	        logger.info("写出文件完毕!路径:"+outFile.getPath());
	        return outFile.getPath();
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
		return "";
	}
	public static void copyFile(String inPath,String outPaht,String fileName) {
		 File file = new File(inPath);
		 File outFile = new File(outPaht+File.separatorChar+fileName);
		 try {
			InputStream in = new FileInputStream(file);
			wirteFile(in, new FileOutputStream(outFile));
	        logger.info("写出文件完毕!路径:"+outFile.getPath());
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
	}
	public static void wirteFile(InputStream in,OutputStream out) {
		byte[] b = new byte[1024];
        int len = 0;
        try {
        	 while((len=in.read(b))!=-1) {
             	out.write(b, 0, len);
             }
        	 out.flush();
        }catch(IOException e) {
        	logger.error("复制文件出错："+e);
        }finally {
            try {
            	if(out!=null) {
    				out.close();
            	}
            	if(in!=null) {
    	            in.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
       
	}
}
