package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util");
	
	public static void copyFile(InputStream is,String outPaht,String fileName) {
		File file = new File(outPaht);
		if(!file.exists()) {
			file.mkdirs();
		}
		File outFile = new File(outPaht+File.separatorChar+fileName);
		try {
			InputStreamReader in = new InputStreamReader(is);
			PrintWriter out = new PrintWriter(outFile);
			char[] c = new char[1024];
	        int len = 0;
	        while((len=in.read(c))!=-1) {
	        	out.write(c, 0, len);
	        }
	        out.close();
	        in.close();
	        logger.info("写出文件完毕!路径:"+outFile.getPath());
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
	}
	public static void copyFile(String inPath,String outPaht,String fileName) {
		 File file = new File(inPath);
		 File outFile = new File(outPaht+File.separatorChar+fileName);
		 try {
			InputStream is = new FileInputStream(file);
			InputStreamReader in = new InputStreamReader(is,"utf8");
			PrintWriter out = new PrintWriter(outFile,"utf8");
			char[] c = new char[1024];
	        int len = 0;
	        while((len=in.read(c))!=-1) {
	        	out.write(c, 0, len);
	        }
	        out.close();
	        in.close();
	        logger.info("写出文件完毕!路径:"+outFile.getPath());
		} catch (IOException e) {
			logger.error("复制文件错误:",e);
		}
	}
}
