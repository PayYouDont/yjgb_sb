package com.gospell.chitong.rdcenter.broadcast.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xeustechnologies.jtar.TarEntry;
import org.xeustechnologies.jtar.TarInputStream;
import org.xeustechnologies.jtar.TarOutputStream;

import com.gospell.chitong.rdcenter.broadcast.commonManage.xml.BaseXML;

public class TarUtil {

	public static final Logger logger = LoggerFactory.getLogger("com.gospell.chitong.rdcenter.broadcast.util.TarUtil");

	/**
	 * 解压归档tar包
	 * @Title: archive 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param inPath
	 * @param @param outPath    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月21日 下午5:44:32
	 */
	public static void archive(String inPath, String outPath) {
		// outPath = outPath.lastIndexOf("\\")==outPath.length()?outPath:outPath+"\\";
		File file = new File(inPath);
		TarInputStream in = null;
		try {
			in = new TarInputStream(new FileInputStream(file));
			TarEntry entry = null;
			while ((entry = in.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File outFile = new File(outPath + File.separatorChar + entry.getName());
				new File(outFile.getParent()).mkdirs();
				OutputStream out = new FileOutputStream(outFile);
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			logger.error("解压tar包出错！", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 生成xml文件并打包成tar包
	 * @Title: createXMLTar 
	 * @Description: TODO(生成xml文件并打包成tar包) 
	 * @param @param map
	 * @param @param outPath
	 * @param @param name
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月20日 下午1:36:30
	 */
	public static void createXMLTar(Map<String,Object> map,String outPath,String name) {
		outPath = outPath.substring(0,outPath.lastIndexOf("\\"));
		String xmlpath = XMLUtil.createXML(map, outPath, name);
		name = name.indexOf(".tar")==-1?name+".tar":name;
		outPath = outPath + File.separatorChar + name;
		OutputStream tar = null;
		TarOutputStream tarOut = null;
		try {
			tar = new FileOutputStream(outPath);
			tarOut = new TarOutputStream(tar);
			File xml = new File(xmlpath);
			tarOut.putNextEntry(new TarEntry(xml,xml.getName()));
			FileUtil.wirteFile(new FileInputStream(xml), tarOut);
			xml.delete();
		}catch(IOException e) {
			logger.error("创建tar包出错："+e);
		}
	}
	/**
	 * 根据tar包路径生成对应回执tar包路径
	 * @Title: getTarByInTar 
	 * @Description: TODO(根据tar包路径生成对应回执tar包路径) 
	 * @param @param tarPath
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws 
	 * @author peiyongdong
	 * @date 2018年6月27日 上午9:13:19
	 */
	public static String getTarByInTar(String tarPath) {
		//d:/tar/xxxxx.tar
		String temPath = tarPath.substring(0,tarPath.indexOf(".tar"));
		//临时文件夹名称
		temPath += File.separatorChar+"temp";
		File tempFile = new File(temPath);
		//查看临时文件夹是否存在
		if(!tempFile.exists()) {
			tempFile.mkdirs();
		}
		archive(tarPath, temPath);
		File[] files = tempFile.listFiles();
		for(int i=0;i<files.length;i++) {
			File file = files[i];
			if(!file.isDirectory()) {
				String fileName = file.getName();
				if(fileName.indexOf("EBDB")!=-1&&fileName.indexOf("EBDS")==-1) {
					//获取BaseXML
					BaseXML xml = XMLUtil.readXML(file,BaseXML.class);
					//获取EBDType
					String EBDType = xml.getEBD_EBDType();
					//获取实际属于哪个子类
					Class<? extends BaseXML> clazz = BaseXML.getClassByEBDType(EBDType);
					String xmlPath = file.getAbsolutePath();
					//获取实际xml实体类
					xml = XMLUtil.readXML(xmlPath, clazz);
					System.out.println(xml);
				}
			}
		}
		return null;
	}
	public static void createTar(String filesPath, String tarPath) {
		
	}
	public static void main(String[] args) {
		//String inPath = "C:\\Users\\pay\\Desktop\\xml\\EBDT_10234000000000001010101010000000000002476.tar";
		//String outPath = "C:\\Users\\pay\\Desktop\\xml\\EBDT_10234000000000001010101010000000000002476";
		String tarPath = "D:\\tar\\get\\EBDT_10234000000000001010101010000000000002889_in.tar";
		getTarByInTar(tarPath);
	}
}
