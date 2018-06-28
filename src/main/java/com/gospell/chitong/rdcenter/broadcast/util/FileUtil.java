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
	
	public static boolean deleteFile(File file) {
		if (file.exists() && file.isFile()) {
			if(file.delete()) {
				logger.info("删除文件"+file.getName()+"成功!");
				return true;
			}else {
				logger.info("删除文件"+file.getName()+"失败!");
				return false;
			}
		}
		logger.info("删除单个文件" + file.getName() + "失败！");
		return false;
	}
	
	public static boolean deleteDirectory(File dir) {
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dir.exists()) || (!dir.isDirectory())) {
        	logger.info("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
        	  // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i]);
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]);
                if (!flag)
                    break;
            }
        }
        if (!flag) {
        	logger.info("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dir.delete()) {
        	logger.info("删除目录" + dir + "成功！");
            return true;
        } else {
        	logger.info("删除目录失败！");
            return false;
        }
	}
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			return false;
		}
		if(file.isFile()) {
			deleteFile(file);
			return true;
		}else{
			deleteDirectory(file);
			return true;
		}
	}
}
